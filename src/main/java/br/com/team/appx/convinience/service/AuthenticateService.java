package br.com.team.appx.convinience.service;


import br.com.team.appx.convinience.dto.CurrentUserDto;
import br.com.team.appx.convinience.dto.UserDto;
import br.com.team.appx.convinience.dto.UserMobileDto;
import br.com.team.appx.convinience.exception.UserInexistenteException;
import br.com.team.appx.convinience.model.entity.Role;
import br.com.team.appx.convinience.model.entity.User;
import br.com.team.appx.convinience.model.entity.UserId;
import br.com.team.appx.convinience.security.Criptografia;
import br.com.team.appx.convinience.security.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class AuthenticateService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<Object> authenticateMobile(UserMobileDto userMobileDto) throws UsernameNotFoundException {


     //   Boolean existsUser = this.userService.verifyExistsMobile(userMobileDto);        // entity does exist

        Optional<User> usuarioOptional = getCurrentUserMobile(userMobileDto);
        User user = usuarioOptional.orElseThrow(UserInexistenteException::new);

        String token = this.jwtTokenUtil.generateToken(user);
        CurrentUserDto currentUserDto = this.modelMapper.map(user, CurrentUserDto.class);
        currentUserDto.setAccessToken(token);
        currentUserDto.setUserId(new UserId(Criptografia.md5(userMobileDto.getPhone()), Criptografia.md5(userMobileDto.getFiretoken())));

        return ResponseEntity.ok(currentUserDto);
    }


    private Optional<User> getCurrentUserMobile(UserMobileDto userMobileDto) {
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
        Optional<User> usuarioOptional = this.userService.findUser(userId);
        User user = usuarioOptional.orElseThrow(UserInexistenteException::new);
        if (user.getRole() == Role.USER) {
            return null;
        }

        return user;
    }

}
