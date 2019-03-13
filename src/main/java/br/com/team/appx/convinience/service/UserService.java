package br.com.team.appx.convinience.service;

import br.com.team.appx.convinience.model.entity.User;
import br.com.team.appx.convinience.model.entity.UserId;
import br.com.team.appx.convinience.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        user.setCreatedOn(this.getCreatedOnById(user.getId()));
        user.setUpdatedOn(LocalDateTime.now());

        return this.userRepository.save(user);
    }

    public User findUser(UserId id) {

        return   this.userRepository.getOne(id);       // entity does exist

    }

    public Boolean verifyExists(UserId id) {

        return  this.userRepository.existsById(id);       // entity does exist

    }

    private LocalDateTime getCreatedOnById(UserId id) {
        if (id == null) {
            return LocalDateTime.now();
        }

        User user = this.userRepository.findById(id)
                .orElse(null);

        if (user == null) {
            return LocalDateTime.now();
        }

        return user.getCreatedOn();
    }
}
