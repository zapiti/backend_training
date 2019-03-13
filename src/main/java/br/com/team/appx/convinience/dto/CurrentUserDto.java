package br.com.team.appx.convinience.dto;

import lombok.Data;

@Data
public class CurrentUserDto {

    private String username;

    private Long userId;

    private String password;

    private String accessToken;
}
