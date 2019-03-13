package br.com.team.appx.convinience.service;


import br.com.team.appx.convinience.dto.CurrentUserDto;
import br.com.team.appx.convinience.dto.UserCredentialsDto;
import br.com.team.appx.convinience.model.entity.User;
import br.com.team.appx.convinience.model.entity.UserId;
import br.com.team.appx.convinience.security.CurrentUser;
import br.com.team.appx.convinience.security.JwtTokenUtil;
import br.com.team.appx.convinience.util.MyExeptionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
public class AuthenticateService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ModelMapper modelMapper;

    public CurrentUserDto authenticate(UserCredentialsDto userCredentialsDto)throws IOException {


        User users = this.userService.findUser(new UserId(userCredentialsDto.getCpf(),userCredentialsDto.getPassword()));        // entity does exist


        CurrentUser currentUser = new CurrentUser(
                users.getUsername(),
                userCredentialsDto.getPassword(),
                users.getId().getCpf()
        );

        String token = this.jwtTokenUtil.generateToken(currentUser);

        CurrentUserDto currentUserDto = this.modelMapper.map(currentUser, CurrentUserDto.class);
        currentUserDto.setAccessToken(token);

        return currentUserDto;
    }
}
