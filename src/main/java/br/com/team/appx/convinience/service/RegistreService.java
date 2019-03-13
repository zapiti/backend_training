package br.com.team.appx.convinience.service;


import br.com.team.appx.convinience.dto.CurrentUserDto;
import br.com.team.appx.convinience.dto.UserCredentialsDto;
import br.com.team.appx.convinience.model.entity.User;
import br.com.team.appx.convinience.model.entity.UserId;
import br.com.team.appx.convinience.security.CurrentUser;
import br.com.team.appx.convinience.security.JwtTokenUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public CurrentUserDto createUser(UserCredentialsDto userCredentialsDto)throws IOException {

        Boolean users = this.userService.verifyExists(new UserId(userCredentialsDto.getCpf(),userCredentialsDto.getPassword()));        // entity does exist
        CurrentUser currentUser;
        if (users){
            currentUser = getExistUser(userCredentialsDto);
        }else{
            currentUser = creatNewUser(userCredentialsDto);
        }


        String token = this.jwtTokenUtil.generateToken(currentUser);

        CurrentUserDto currentUserDto = this.modelMapper.map(currentUser, CurrentUserDto.class);

        currentUserDto.setAccessToken(token);




        return currentUserDto;
    }

    private CurrentUser getExistUser(UserCredentialsDto userCredentialsDto) {
        CurrentUser currentUser;//todo mudar
        User user = this.userService.findUser(new UserId(userCredentialsDto.getCpf(),userCredentialsDto.getPassword()));
        currentUser = new CurrentUser(
                user.getUsername(),
                userCredentialsDto.getPassword(),
                user.getId().getCpf()
       );
        return currentUser;
    }

    private CurrentUser creatNewUser(UserCredentialsDto userCredentialsDto) {
        CurrentUser currentUser;
        currentUser = new CurrentUser(
                userCredentialsDto.getUsername(),
                userCredentialsDto.getPassword(),
                userCredentialsDto.getCpf()
        );

        User user = new User();
        user.setId(new UserId(currentUser.getCpf(),currentUser.getPassword()));
        user.setUsername(currentUser.getUsername());

        this.userService.saveUser(user);
        return currentUser;
    }
}
