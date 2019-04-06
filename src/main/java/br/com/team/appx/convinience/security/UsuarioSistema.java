package br.com.team.appx.convinience.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UsuarioSistema extends User {

    private static final long serialVersionUID = 1L;

    private @Getter
    User usuario;

    public UsuarioSistema() {
        super(SecurityContextHolder.getContext().getAuthentication().getName(), "",
                SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    }

    public UsuarioSistema(User usuario, Collection<? extends GrantedAuthority> authorities) {
        super(String.valueOf(usuario.getUsername()), usuario.getPassword(),true, true, true,
                true, authorities);


        this.usuario = usuario;
    }

    public UsuarioSistema(User usuario, String username, List<String> authorities) {
        super(username, "", mapToGrantedAuthorities(authorities));
        this.usuario = usuario;
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return Optional.ofNullable(authorities).orElseGet(Collections::emptyList).stream()
                .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());
    }


}
