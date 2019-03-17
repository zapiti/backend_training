package br.com.team.appx.convinience.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {

        @NotEmpty
        private String email;

        @NotNull
        private String password;


}
