package br.com.team.appx.convinience.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserCredentialsDto {

    @NotEmpty
    private String username;

    @NotNull
    private Long cpf;

    @NotNull
    private String password;
}
