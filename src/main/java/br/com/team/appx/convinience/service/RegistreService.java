package br.com.team.appx.convinience.service;


import br.com.team.appx.convinience.dto.CurrentUserDto;
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
public class RegistreService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Object> createUser(UserMobileDto userMobileDto)throws IOException {

        Boolean users = this.userService.verifyExistsMobile(userMobileDto);        // entity does exist
        User user;
        if (users){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario ja existe");
        }else{
            user = creatNewUser(userMobileDto);
        }


        String token = this.jwtTokenUtil.generateToken(user);

        CurrentUserDto currentUserDto = this.modelMapper.map(user, CurrentUserDto.class);
        currentUserDto.setUserId(new UserId( Criptografia.md5(userMobileDto.getPhone()), Criptografia.md5(userMobileDto.getFiretoken())));
        currentUserDto.setAccessToken(token);

        return ResponseEntity.ok(currentUserDto);
    }

    private User creatNewUser(UserMobileDto userMobileDto) {

        User user = new User();
        user.setTelephone(userMobileDto.getPhone());
        user.setFiretoken(userMobileDto.getFiretoken());
        user.setRole(Role.USER);
        user.setUserId(new UserId(Criptografia.md5(userMobileDto.getPhone()), Criptografia.md5(userMobileDto.getFiretoken())));
        this.userService.saveUser(user);

        return user;
    }
}
