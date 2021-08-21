package org.challenge.scope.service.impl;

import static java.lang.String.format;
import static org.challenge.util.exception.ErrorMessageCode.ERROR_COLLABORATOR_DOES_NOT_EXIST_001;

import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.challenge.entity.CollaboratorEntity;
import org.challenge.scope.repository.CollaboratorRepository;
import org.challenge.scope.service.CollaboratorService;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public final class CollaboratorServiceImpl implements CollaboratorService {

    private final CollaboratorRepository collaboratorRepository;

    @Override
    public void deleteById(Long id) throws NotFoundException {
        boolean exist = collaboratorRepository.existsById(id);

        if (exist) {
            collaboratorRepository.deleteById(id);
        } else {
            throw new NotFoundException(format(ERROR_COLLABORATOR_DOES_NOT_EXIST_001.getName(), id));
        }
    }

    @Override
    public CollaboratorEntity create(final CollaboratorEntity collaboratorEntity) {
        return collaboratorRepository.save(collaboratorEntity);
    }

    @Override
    public CollaboratorEntity update(final Long id, final CollaboratorEntity collaboratorEntity) throws NotFoundException {
        final boolean exist = collaboratorRepository.existsById(id);

        if (exist) {
            return collaboratorRepository.save(collaboratorEntity);
        }

        throw new NotFoundException(format(ERROR_COLLABORATOR_DOES_NOT_EXIST_001.getName(), id));
    }

    @Override
    public List<CollaboratorEntity> findAll() {
        return collaboratorRepository.findAll();
    }

    @Override
    public CollaboratorEntity findById(final Long id) throws NotFoundException {
        final Optional<CollaboratorEntity> collaborator = collaboratorRepository.findById(id);

        if (collaborator.isEmpty()) {
            throw new NotFoundException(format(ERROR_COLLABORATOR_DOES_NOT_EXIST_001.getName(), id));
        }

        return collaborator.get();
    }
}
