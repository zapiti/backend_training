package br.com.team.appx.convinience.controllers;

import br.com.team.appx.convinience.dto.CurrentUserDto;
import br.com.team.appx.convinience.dto.UserCredentialsDto;
import br.com.team.appx.convinience.service.AuthenticateService;
import br.com.team.appx.convinience.service.RegistreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/registre")
public class RegistreController {
    @Autowired
    private RegistreService registreService;

    @PostMapping()
    public CurrentUserDto createUser(@Valid @RequestBody UserCredentialsDto userCredentialsDto) throws IOException {
        return this.registreService.createUser(userCredentialsDto);
    }
}