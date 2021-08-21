package org.challenge.mapper;


import static org.mapstruct.NullValueMappingStrategy.RETURN_DEFAULT;
import static org.mapstruct.ReportingPolicy.ERROR;

import java.util.List;
import org.challenge.dto.CollaboratorDTO;
import org.challenge.entity.CollaboratorEntity;
import org.mapstruct.Mapper;

@Mapper(
    componentModel = "spring",
    nullValueMappingStrategy = RETURN_DEFAULT,
    unmappedTargetPolicy = ERROR
)
public interface CollaboratorMapper {

    CollaboratorEntity toEntity(final CollaboratorDTO dto);

    CollaboratorDTO toDto(final CollaboratorEntity entity);

    List<CollaboratorDTO> toDTOList(final List<CollaboratorEntity> source);
}
