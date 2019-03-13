package br.com.team.appx.convinience.security;


import br.com.team.appx.convinience.util.NumberUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

import static br.com.team.appx.convinience.security.JwtConstants.SECRET;

@Component
public class JwtTokenUtil {
    public String generateToken(CurrentUser currentUser) {
        Claims claims = Jwts.claims().setSubject(currentUser.getUsername());

        claims.put("username", currentUser.getUsername());
        claims.put("userId", currentUser.getCpf());
        claims.put("password", currentUser.getPassword());

        return Jwts.builder()
                .setClaims(claims)
                // .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getUserPassordFromToken(String token) {
        return (String) this.getAllClaimsFromToken(token).get("password");
    }

    public Long getUserIdFromToken(String token) {
        return NumberUtils.toLong(this.getAllClaimsFromToken(token).get("userId"));
    }

    public String getUsernameFromToken(String token) {
        return this.getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
