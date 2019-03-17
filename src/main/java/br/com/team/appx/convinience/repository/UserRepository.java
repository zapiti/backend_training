package br.com.team.appx.convinience.repository;

import br.com.team.appx.convinience.dto.UserMobileDto;
import br.com.team.appx.convinience.model.entity.User;
import br.com.team.appx.convinience.model.entity.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByfiretoken(String firetoken);
    User getByfiretoken(String firetoken);
    @Query("select u from User u where u.password = :#{#user.password} and  u.email = :#{#user.email}")
    User findUsersByPasswordFirstname(@Param("user") UserMobileDto user);
}
