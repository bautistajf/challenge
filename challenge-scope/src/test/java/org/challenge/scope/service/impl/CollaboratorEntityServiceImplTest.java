package org.challenge.scope.service.impl;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.challenge.entity.CollaboratorEntityFixtureBuilder.getCollaboratorMock;
import static org.challenge.entity.CollaboratorEntityFixtureBuilder.getCollaboratorsMock;
import static org.challenge.entity.CollaboratorEntityFixtureBuilder.getOptionalCollaboratorMock;
import static org.challenge.util.exception.ErrorMessageCode.ERROR_COLLABORATOR_DOES_NOT_EXIST_001;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.challenge.entity.CollaboratorEntity;
import org.challenge.scope.repository.CollaboratorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CollaboratorEntityServiceImplTest {

    @InjectMocks
    private CollaboratorServiceImpl service;

    @Mock
    private CollaboratorRepository repository;


    @Test
    void collaboratorService_should_call_findAll_repository() {
        when(repository.findAll()).thenReturn(getCollaboratorsMock());

        final List<CollaboratorEntity> collaboratorList = service.findAll();

        verify(repository).findAll();
        assertNotNull(collaboratorList);
        assertEquals(1, collaboratorList.size());
    }

    @Test
    void collaboratorService_should_call_findById_repository() throws NotFoundException {
        when(repository.findById(anyLong())).thenReturn(getOptionalCollaboratorMock());

        final CollaboratorEntity result = service.findById(1L);

        verify(repository).findById(anyLong());
        assertNotNull(result);
    }

    @Test
    void collaboratorService_should_call_findById_repository_throw_exception() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        final Throwable exception = catchThrowable(() -> service.findById(1L));

        verify(repository).findById(anyLong());
        assertNotNull(exception);
        assertEquals(format(ERROR_COLLABORATOR_DOES_NOT_EXIST_001.getName(), 1), exception.getMessage());
    }

    @Test
    void update_should_throw_exception() {
        when(repository.existsById(anyLong())).thenReturn(false);

        final Throwable exception = catchThrowable(() -> service.update(1L, getCollaboratorMock()));

        verify(repository).existsById(anyLong());
        assertNotNull(exception);
        assertEquals(format(ERROR_COLLABORATOR_DOES_NOT_EXIST_001.getName(), 1), exception.getMessage());

    }

    @Test
    void update_should_call_save_repository() throws NotFoundException {
        when(repository.existsById(anyLong())).thenReturn(true);
        when(repository.save(any())).thenReturn(getCollaboratorMock());

        final CollaboratorEntity result = service.update(1L, getCollaboratorMock());

        verify(repository).existsById(anyLong());
        verify(repository).save(any());
        assertNotNull(result);
    }

    @Test
    void delete_should_call_deleteById_repository() throws NotFoundException {
        when(repository.existsById(anyLong())).thenReturn(true);
        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(1L);

        verify(repository).existsById(anyLong());
        verify(repository).deleteById(anyLong());
    }

    @Test
    void deleteById_should_throw_exception() {
        when(repository.existsById(anyLong())).thenReturn(false);

        Throwable exception = catchThrowable(() -> service.deleteById(1L));

        verify(repository).existsById(anyLong());
        assertNotNull(exception);
        assertEquals(format(ERROR_COLLABORATOR_DOES_NOT_EXIST_001.getName(), 1), exception.getMessage());

    }

    @Test
    void create_should_call_save() throws NotFoundException {
        when(repository.save(any())).thenReturn(getCollaboratorMock());
        final CollaboratorEntity result = service.create(getCollaboratorMock());

        verify(repository).save(any());
    }


}
