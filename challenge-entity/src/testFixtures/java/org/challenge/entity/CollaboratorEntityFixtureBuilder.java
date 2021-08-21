package org.challenge.entity;

import java.util.List;
import java.util.Optional;

public interface CollaboratorEntityFixtureBuilder {

    static List<CollaboratorEntity> getCollaboratorsMock() {
        return List.of(CollaboratorEntity.builder().build());
    }

    static Optional<CollaboratorEntity> getOptionalCollaboratorMock() {
        return Optional.of(getCollaboratorMock());
    }

    static CollaboratorEntity getCollaboratorMock() {
        return CollaboratorEntity.builder()
            .id(1L)
            .name("Javier")
            .lastName("Bautista")
            .address("Mateu Monserrat")
            .city("Mallorca")
            .email("email@gmail.com")
            .phoneNumber("683126520")
            .build();
    }
}
