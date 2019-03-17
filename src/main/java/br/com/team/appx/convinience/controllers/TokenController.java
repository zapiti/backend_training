package br.com.team.appx.convinience.controllers;

import br.com.team.appx.convinience.dto.UserDto;
import br.com.team.appx.convinience.dto.UserMobileDto;
import br.com.team.appx.convinience.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/token")
public class TokenController {
    @Autowired
    private AuthenticateService authenticateService;

    @PostMapping()
    public ResponseEntity<Object> createTokenMobile(@Valid @RequestBody UserMobileDto userMobileDto) throws IOException {
        return this.authenticateService.authenticateMobile(userMobileDto);
    }

    @GetMapping()
    public ResponseEntity<Object> createToken(@Valid @RequestBody UserDto userDto) throws IOException {
        return this.authenticateService.authenticate(userDto);
    }
}