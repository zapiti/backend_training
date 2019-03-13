package br.com.team.appx.convinience.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Set;

@Getter
public class CurrentUser implements UserDetails {

    private final Long cpf;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public CurrentUser(
            String username,
            String password,
            Long cpf

    ) {


        this.cpf = cpf;
        this.username = username;
        this.password = password;
        this.authorities = Collections.emptySet();
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }
}
