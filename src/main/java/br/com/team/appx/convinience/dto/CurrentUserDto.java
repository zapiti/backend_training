package br.com.team.appx.convinience.dto;

import br.com.team.appx.convinience.model.entity.UserId;
import lombok.Data;

@Data
public class CurrentUserDto {

    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private Long cpf;
    private String username;
    private UserId userId;
    private String accessToken;


}
