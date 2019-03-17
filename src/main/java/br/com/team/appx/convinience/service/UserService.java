package br.com.team.appx.convinience.service;

import br.com.team.appx.convinience.dto.UserMobileDto;
import br.com.team.appx.convinience.model.entity.User;
import br.com.team.appx.convinience.model.entity.UserId;
import br.com.team.appx.convinience.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
class UserService {
    @Autowired
    private UserRepository userRepository;

    User saveUser(User user) {
        user.setCreatedOn(this.getCreatedOnById(user.getId()));
        user.setUpdatedOn(LocalDateTime.now());

        return this.userRepository.save(user);
    }

    User findUserMobile(UserMobileDto userMobileDto) {

        return this.userRepository.getByfiretoken(userMobileDto.getFiretoken());

    }

    Boolean verifyExistsMobile(UserMobileDto userMobileDto) {
        return this.userRepository.existsByfiretoken(userMobileDto.getFiretoken());
    }

    private LocalDateTime getCreatedOnById(Long id) {
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
