package br.com.team.appx.convinience.controllers;

import br.com.team.appx.convinience.dto.UserMobileDto;
import br.com.team.appx.convinience.service.RegistreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserMobileDto userMobileDto) throws IOException {
        return this.registreService.createUserMobile(userMobileDto);
    }
}