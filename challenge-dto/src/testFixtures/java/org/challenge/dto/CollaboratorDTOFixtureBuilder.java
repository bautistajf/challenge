package org.challenge.dto;

public interface CollaboratorDTOFixtureBuilder {

    static CollaboratorDTO getCollaboratorDTOMock() {
        return CollaboratorDTO.builder()
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
