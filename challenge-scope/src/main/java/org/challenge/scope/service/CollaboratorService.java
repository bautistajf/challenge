package org.challenge.scope.service;

import java.util.List;
import javassist.NotFoundException;
import org.challenge.entity.CollaboratorEntity;

public interface CollaboratorService {

    void deleteById(Long id) throws NotFoundException;

    CollaboratorEntity create(final CollaboratorEntity collaboratorEntity);

    CollaboratorEntity update(final Long id, final CollaboratorEntity collaboratorEntity) throws NotFoundException;

    List<CollaboratorEntity> findAll();

    CollaboratorEntity findById(final Long id) throws NotFoundException;
}
