package br.com.team.appx.convinience.security;

import br.com.team.appx.convinience.model.entity.Role;
import br.com.team.appx.convinience.model.entity.UserId;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Set;

@Getter
public class CurrentUser implements UserDetails {

    private String first_name;
    private String last_name;
    private String email;
    private final String phone;
    private Long cpf;
    private String username;
    private UserId userId;
    private final String firetoken;
    private final Role role;

    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public CurrentUser(
            String phone,
            String firetoken,
            Role role
    ) {


        this.role = role;
        this.phone = phone;
        this.firetoken = firetoken;
        this.authorities = Collections.emptySet();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    @Override
    public String getPassword() {
        return null;
    }
}
