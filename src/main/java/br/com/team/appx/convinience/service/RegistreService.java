package br.com.team.appx.convinience.service;


import br.com.team.appx.convinience.dto.CurrentUserDto;
import br.com.team.appx.convinience.dto.UserMobileDto;
import br.com.team.appx.convinience.model.Role;
import br.com.team.appx.convinience.model.User;
import br.com.team.appx.convinience.model.UserId;
import br.com.team.appx.convinience.security.Criptografia;
import br.com.team.appx.convinience.security.JwtTokenUtil;
import br.com.team.appx.convinience.service.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RegistreService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Object> createUserMobile(UserMobileDto userMobileDto) throws IOException {

        Boolean users = this.userService.verifyExistsMobile(userMobileDto);        // entity does exist
        User user;
        if (users) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe");
        } else {
            user = saveUserMobile(userMobileDto);
        }
        String token = this.jwtTokenUtil.generateToken(user);

        CurrentUserDto currentUserDto = this.modelMapper.map(user, CurrentUserDto.class);
        currentUserDto.setUserId(new UserId(Criptografia.md5(userMobileDto.getPhone()), Criptografia.md5(userMobileDto.getFiretoken())));
        currentUserDto.setAccessToken(token);

        return ResponseEntity.ok(currentUserDto);
    }


    private User saveUserMobile(UserMobileDto userMobileDto) {

        User user = new User();
        user.setFiretoken(userMobileDto.getFiretoken());
        user.setRole(Role.USER);
        user.setUserId(new UserId(Criptografia.md5(userMobileDto.getPhone()), Criptografia.md5(userMobileDto.getFiretoken())));
        this.userService.saveUser(user);

        return user;
    }
}
