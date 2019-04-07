package br.com.team.appx.convinience.controllers.user;

import br.com.team.appx.convinience.model.User;
import br.com.team.appx.convinience.security.JwtTokenUtil;
import br.com.team.appx.convinience.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user/update")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PutMapping()
    public ResponseEntity<Object> updateUser(@RequestBody User user, @RequestHeader String Authorization) throws IOException {
        String token = Authorization.replace("Bearer", "").trim();
        return this.userService.updateUser(jwtTokenUtil.getUserId(token), user);
    }
}