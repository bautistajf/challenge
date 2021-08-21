package org.challenge.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;
import java.time.LocalDateTime;
import java.util.List;
import javassist.NotFoundException;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.challenge.dto.CollaboratorDTO;
import org.challenge.dto.ErrorDTO;
import org.challenge.mapper.CollaboratorMapper;
import org.challenge.scope.service.CollaboratorService;
import org.challenge.util.controller.ControllerHelper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
    value = "collaborators",
    produces = APPLICATION_JSON_VALUE
)
@Api(value = "Collaborator resource rest endpoint")
@RequiredArgsConstructor
public class CollaboratorController {

    private final CollaboratorService service;

    private final CollaboratorMapper mapper;

    @ApiOperation(
        value = "Get all collaborators",
        notes = "Returns all collaborators details",
        response = CollaboratorDTO.class,
        responseHeaders = {
            @ResponseHeader(name = ControllerHelper.X_PROCESSTIME,
                description = ControllerHelper.X_PROCESSTIME_DESC, response = Integer.class),
            @ResponseHeader(name = ControllerHelper.X_INIT_TIMESTAMP,
                description = ControllerHelper.X_INIT_TIMESTAMP_DESC, response = LocalDateTime.class),
            @ResponseHeader(name = ControllerHelper.X_REQUESTHOST,
                description = ControllerHelper.X_REQUESTHOST_DESC, response = String.class),
            @ResponseHeader(name = ControllerHelper.X_SERVICENAME,
                description = ControllerHelper.X_SERVICENAME_DESC, response = String.class),
            @ResponseHeader(name = ControllerHelper.X_VERSION,
                description = ControllerHelper.X_VERSION_DESC, response = String.class)
        }
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized", response = ErrorDTO.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Failure", response = ErrorDTO.class)
    })
    @GetMapping
    public ResponseEntity<List<CollaboratorDTO>> getAllCollaborators() {
        final LocalDateTime timestamp = LocalDateTime.now();
        final HttpHeaders httpHeaders = ControllerHelper.getHeaders(timestamp);

        final List<CollaboratorDTO> collaborators = mapper.toDTOList(service.findAll());

        ControllerHelper.setProcessTime(timestamp, httpHeaders);
        return new ResponseEntity<>(collaborators, httpHeaders, OK);
    }

    @ApiOperation(
        value = "Get collaborator by id",
        notes = "Returns the complete collaborator by id. ",
        response = CollaboratorDTO.class,
        responseHeaders = {
            @ResponseHeader(name = ControllerHelper.X_PROCESSTIME,
                description = ControllerHelper.X_PROCESSTIME_DESC, response = Integer.class),
            @ResponseHeader(name = ControllerHelper.X_INIT_TIMESTAMP,
                description = ControllerHelper.X_INIT_TIMESTAMP_DESC, response = LocalDateTime.class),
            @ResponseHeader(name = ControllerHelper.X_REQUESTHOST,
                description = ControllerHelper.X_REQUESTHOST_DESC, response = String.class),
            @ResponseHeader(name = ControllerHelper.X_SERVICENAME,
                description = ControllerHelper.X_SERVICENAME_DESC, response = String.class),
            @ResponseHeader(name = ControllerHelper.X_VERSION,
                description = ControllerHelper.X_VERSION_DESC, response = String.class)
        }
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized", response = ErrorDTO.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Failure", response = ErrorDTO.class)
    })
    @GetMapping("/{id}")
    public ResponseEntity<CollaboratorDTO> getCollaboratorById(@PathVariable("id") long id) throws NotFoundException {
        final LocalDateTime timestamp = LocalDateTime.now();
        final HttpHeaders httpHeaders = ControllerHelper.getHeaders(timestamp);

        final CollaboratorDTO collaborator = mapper.toDto(service.findById(id));

        ControllerHelper.setProcessTime(timestamp, httpHeaders);
        return new ResponseEntity<>(collaborator, httpHeaders, OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) throws NotFoundException {
        final LocalDateTime timestamp = LocalDateTime.now();
        final HttpHeaders httpHeaders = ControllerHelper.getHeaders(timestamp);

        service.deleteById(id);

        ControllerHelper.setProcessTime(timestamp, httpHeaders);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(
        value = "Create new collaborator",
        notes = "Returns the complete collaborator created",
        response = CollaboratorDTO.class,
        responseHeaders = {
            @ResponseHeader(name = ControllerHelper.X_PROCESSTIME,
                description = ControllerHelper.X_PROCESSTIME_DESC, response = Integer.class),
            @ResponseHeader(name = ControllerHelper.X_INIT_TIMESTAMP,
                description = ControllerHelper.X_INIT_TIMESTAMP_DESC, response = LocalDateTime.class),
            @ResponseHeader(name = ControllerHelper.X_REQUESTHOST,
                description = ControllerHelper.X_REQUESTHOST_DESC, response = String.class),
            @ResponseHeader(name = ControllerHelper.X_SERVICENAME,
                description = ControllerHelper.X_SERVICENAME_DESC, response = String.class),
            @ResponseHeader(name = ControllerHelper.X_VERSION,
                description = ControllerHelper.X_VERSION_DESC, response = String.class)
        }
    )
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created"),
        @ApiResponse(code = 401, message = "Unauthorized", response = ErrorDTO.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Failure", response = ErrorDTO.class)
    })
    @PostMapping
    public ResponseEntity<CollaboratorDTO> create(@Valid @RequestBody final CollaboratorDTO collaborator) {
        final LocalDateTime timestamp = LocalDateTime.now();
        final HttpHeaders httpHeaders = ControllerHelper.getHeaders(timestamp);

        final CollaboratorDTO result = mapper.toDto(service.create(mapper.toEntity(collaborator)));

        ControllerHelper.setProcessTime(timestamp, httpHeaders);

        return new ResponseEntity<>(result, httpHeaders, CREATED);
    }

    @ApiOperation(
        value = "Update o create collaborator",
        notes = "Returns the complete collaborator updated or created",
        response = CollaboratorDTO.class,
        responseHeaders = {
            @ResponseHeader(name = ControllerHelper.X_PROCESSTIME,
                description = ControllerHelper.X_PROCESSTIME_DESC, response = Integer.class),
            @ResponseHeader(name = ControllerHelper.X_INIT_TIMESTAMP,
                description = ControllerHelper.X_INIT_TIMESTAMP_DESC, response = LocalDateTime.class),
            @ResponseHeader(name = ControllerHelper.X_REQUESTHOST,
                description = ControllerHelper.X_REQUESTHOST_DESC, response = String.class),
            @ResponseHeader(name = ControllerHelper.X_SERVICENAME,
                description = ControllerHelper.X_SERVICENAME_DESC, response = String.class),
            @ResponseHeader(name = ControllerHelper.X_VERSION,
                description = ControllerHelper.X_VERSION_DESC, response = String.class)
        }
    )
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 401, message = "Unauthorized", response = ErrorDTO.class),
        @ApiResponse(code = 403, message = "Forbidden", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Failure", response = ErrorDTO.class)
    })
    @PutMapping("/{id}")
    public ResponseEntity<CollaboratorDTO> update(@PathVariable("id") final long id, @Valid @RequestBody final CollaboratorDTO collaborator)
        throws NotFoundException {
        final LocalDateTime timestamp = LocalDateTime.now();
        final HttpHeaders httpHeaders = ControllerHelper.getHeaders(timestamp);

        collaborator.setId(id);
        final CollaboratorDTO result = mapper.toDto(service.update(id, mapper.toEntity(collaborator)));

        ControllerHelper.setProcessTime(timestamp, httpHeaders);

        return new ResponseEntity<>(result, httpHeaders, OK);
    }
}
