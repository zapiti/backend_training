package br.com.team.appx.convinience.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserMobileDto {

    @NotEmpty
    private String phone;

    @NotNull
    private String firetoken;



}
