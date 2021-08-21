package org.challenge.controller;

import static org.challenge.dto.CollaboratorDTOFixtureBuilder.getCollaboratorDTOMock;
import static org.challenge.entity.CollaboratorEntityFixtureBuilder.getCollaboratorMock;
import static org.challenge.entity.CollaboratorEntityFixtureBuilder.getOptionalCollaboratorMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.List;
import org.challenge.configuration.WebConfig;
import org.challenge.dto.CollaboratorDTO;
import org.challenge.mapper.CollaboratorMapperImpl;
import org.challenge.scope.repository.CollaboratorRepository;
import org.challenge.scope.service.impl.CollaboratorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@ContextConfiguration(classes = {
    CollaboratorController.class,
    WebConfig.class,
    CollaboratorServiceImpl.class,
    CollaboratorMapperImpl.class

})
class CollaboratorControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Spy
    protected final Jackson2ObjectMapperBuilder objectMapperBuilder = new WebConfig().jacksonBuilder();

    @MockBean
    private CollaboratorRepository repository;


    @Test
    void getAllCollaborators_should_return_a_list_of_collaborator() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/collaborators")
            .contentType("application/json")
            .accept("application/json")
            .characterEncoding("utf-8"))
            .andExpect(status().is2xxSuccessful());

        final List<CollaboratorDTO> result = objectMapperBuilder.build().readValue(resultActions.andReturn().getResponse().getContentAsByteArray(),
            new TypeReference<List<CollaboratorDTO>>() {
            });

        assertEquals(0, result.size());
    }

    @Test
    void getCollaboratorById_should_return_a_collaborator() throws Exception {
        when(repository.findById(anyLong())).thenReturn(getOptionalCollaboratorMock());

        final ResultActions resultActions = mockMvc.perform(get("/collaborators/1")
            .contentType("application/json")
            .accept("application/json")
            .characterEncoding("utf-8"))
            .andExpect(status().is2xxSuccessful());

        final CollaboratorDTO result = objectMapperBuilder.build().readValue(resultActions.andReturn().getResponse().getContentAsByteArray(),
            CollaboratorDTO.class);

        assertEquals(getCollaboratorDTOMock(), result);
    }

    @Test
    void delete_should_return_success() throws Exception {
        when(repository.existsById(anyLong())).thenReturn(true);
        doNothing().when(repository).deleteById(anyLong());

        mockMvc.perform(delete("/collaborators/1")
            .contentType("application/json")
            .accept("application/json")
            .characterEncoding("utf-8"))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    void create_should_return_bad_request() throws Exception {
        final ResultActions resultActions = mockMvc.perform(post("/collaborators")
            .contentType("application/json")
            .accept("application/json")
            .characterEncoding("utf-8")
            .content(objectMapperBuilder.build().writeValueAsString(
                CollaboratorDTO.builder().build()
            )))
            .andExpect(status().isBadRequest())
            .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
    }

    @Test
    void create_should_return_success() throws Exception {
        mockMvc.perform(post("/collaborators")
            .contentType("application/json")
            .accept("application/json")
            .characterEncoding("utf-8")
            .content(objectMapperBuilder.build().writeValueAsString(
                getCollaboratorDTOMock()
            )))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    void update_should_return_success() throws Exception {
        when(repository.existsById(anyLong())).thenReturn(true);
        when(repository.save(any())).thenReturn(getCollaboratorMock());

        mockMvc.perform(put("/collaborators/1")
            .contentType("application/json")
            .accept("application/json")
            .characterEncoding("utf-8")
            .content(objectMapperBuilder.build().writeValueAsString(
                getCollaboratorDTOMock()
            )))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    void update_should_return_bad_request() throws Exception {
        final CollaboratorDTO dto = getCollaboratorDTOMock();
        dto.setEmail("test");

        mockMvc.perform(put("/collaborators/1")
            .contentType("application/json")
            .accept("application/json")
            .characterEncoding("utf-8")
            .content(objectMapperBuilder.build().writeValueAsString(
                dto
            )))
            .andExpect(status().isBadRequest());
    }
}
