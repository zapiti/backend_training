package br.com.team.appx.convinience.controllers;

import br.com.team.appx.convinience.dto.UserMobileDto;
import br.com.team.appx.convinience.model.entity.User;
import br.com.team.appx.convinience.security.JwtTokenUtil;
import br.com.team.appx.convinience.service.RegistreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/registre")
public class RegistreController {

    @Autowired
    private RegistreService registreService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping()
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserMobileDto userMobileDto) throws IOException {
        return this.registreService.createUserMobile(userMobileDto);
    }

    @PutMapping()
    public ResponseEntity<Object> updateUser(@RequestBody User user, @RequestHeader String token) throws IOException {


        return this.registreService.updateUser(jwtTokenUtil.getUserId(token),user);
    }
}