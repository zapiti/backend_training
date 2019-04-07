package br.com.team.appx.convinience.service.user;

import br.com.team.appx.convinience.dto.UserMobileDto;
import br.com.team.appx.convinience.exception.UserInexistenteException;
import br.com.team.appx.convinience.model.User;
import br.com.team.appx.convinience.model.UserId;
import br.com.team.appx.convinience.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        user.setCreatedOn(this.getCreatedOnById(user.getId()));
        user.setUpdatedOn(LocalDateTime.now());

        return this.userRepository.save(user);
    }

    public Optional<User> findUserMobile(UserMobileDto userMobileDto) {
        return this.userRepository.getByfiretoken(userMobileDto.getFiretoken());
    }

    public Optional<User> findUser(UserId userId) {
        return this.userRepository.findUsersByPasswordFirstname(userId);
    }

    public Boolean verifyExistsMobile(UserMobileDto userMobileDto) {
        return this.userRepository.existsByfiretoken(userMobileDto.getFiretoken());
    }
    public Boolean verifyExists(UserId userId) {
        return this.userRepository.existsByUserId(userId);
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


    public ResponseEntity<Object> updateUser(UserId userId, User userdata) throws UserInexistenteException {

        Optional<User> usuarioOptional = findUser(userId);
        User user = usuarioOptional.orElseThrow(UserInexistenteException::new);

        if (userdata.getCpf() != null) {
            user.setCpf(userdata.getCpf());
        }
        if (userdata.getFirst_name() != null) {
            user.setFirst_name(userdata.getFirst_name());
        }
        if (userdata.getLast_name() != null) {
            user.setLast_name(userdata.getLast_name());
        }
        if (userdata.getEmail() != null) {
            user.setEmail(userdata.getEmail());
        }

        return ResponseEntity.ok(saveUser(user));
    }

}
