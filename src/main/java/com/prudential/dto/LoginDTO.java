package com.prudential.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Login data transfer object used to pass login data to end
 * points.
 *
 * @author Hu Cai
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginDTO {

    @ApiModelProperty(readOnly = true)
    private Long id;

    @ApiModelProperty(readOnly = true)
    private String created;

    @NotBlank
    @NotBlank(message = "User name can not be blank or null!")
    private String name;

    @Email
    @NotBlank(message = "User email can not be blank or null!")
    private String email;
}