package br.com.team.appx.convinience.service;


import br.com.team.appx.convinience.dto.CurrentUserDto;
import br.com.team.appx.convinience.dto.UserDto;
import br.com.team.appx.convinience.dto.UserMobileDto;
import br.com.team.appx.convinience.model.entity.Role;
import br.com.team.appx.convinience.model.entity.User;
import br.com.team.appx.convinience.model.entity.UserId;
import br.com.team.appx.convinience.security.Criptografia;
import br.com.team.appx.convinience.security.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AuthenticateService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Object> authenticateMobile(UserMobileDto userMobileDto) throws IOException {


        Boolean users = this.userService.verifyExistsMobile(userMobileDto);        // entity does exist
        User user;

        if (users) {
            user = getCurrentUserMobile(userMobileDto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não encontrado");
        }

        String token = this.jwtTokenUtil.generateToken(user);
        CurrentUserDto currentUserDto = this.modelMapper.map(user, CurrentUserDto.class);
        currentUserDto.setAccessToken(token);
        currentUserDto.setUserId(new UserId(Criptografia.md5(userMobileDto.getPhone()), Criptografia.md5(userMobileDto.getFiretoken())));
        return ResponseEntity.ok(currentUserDto);
    }


    private User getCurrentUserMobile(UserMobileDto userMobileDto) {
        return this.userService.findUserMobile(userMobileDto);
    }


    public ResponseEntity<Object> authenticate(UserDto userDto) throws IOException {

        UserId userId = new UserId(Criptografia.md5(userDto.getEmail()), Criptografia.md5(userDto.getPassword()));

        Boolean users = this.userService.verifyExists(userId);        // entity does exist
        User user = null;

        if (users) {
            user = getCurrentUser(userId);
        }
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario não encontrado");
        }

        String token = this.jwtTokenUtil.generateToken(user);
        CurrentUserDto currentUserDto = this.modelMapper.map(user, CurrentUserDto.class);
        currentUserDto.setAccessToken(token);
        currentUserDto.setUserId(userId);

        return ResponseEntity.ok(currentUserDto);
    }


    private User getCurrentUser(UserId userId) {
        User user = this.userService.findUser(userId);
        if (user.getRole() == Role.USER) {
            return null;
        }

        return user;
    }

}
