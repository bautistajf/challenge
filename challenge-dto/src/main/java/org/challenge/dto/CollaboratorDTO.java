package org.challenge.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Collaborator", description = "Collaborator detail")
public final class CollaboratorDTO {

    private Long id;

    @ApiModelProperty(
        value = "Name collaborator.",
        required = true
    )
    @NotNull
    @NotEmpty
    @Size(min = 2, message = "collaborator name should have at least 2 characters")
    private String name;

    @ApiModelProperty(
        value = "LastName collaborator.",
        required = true
    )
    @NotNull
    @NotEmpty
    @Size(min = 2, message = "collaborator lastname should have at least 2 characters")
    private String lastName;

    @ApiModelProperty(
        value = "Address collaborator.",
        required = true
    )
    @NotNull
    @NotEmpty
    private String address;

    @ApiModelProperty(
        value = "City collaborator.",
        required = true
    )
    @NotNull
    @NotEmpty
    private String city;

    @ApiModelProperty(
        value = "Email collaborator.",
        required = true
    )
    @NotNull
    @NotEmpty
    @Email
    private String email;

    @ApiModelProperty(
        value = "Phone number collaborator.",
        required = true
    )
    @NotNull
    @NotEmpty
    private String phoneNumber;
}
