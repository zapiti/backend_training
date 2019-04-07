package br.com.team.appx.convinience.security;


import br.com.team.appx.convinience.model.Role;
import br.com.team.appx.convinience.model.User;
import br.com.team.appx.convinience.model.UserId;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.function.Function;

import static br.com.team.appx.convinience.security.JwtConstants.SECRET;

@Component
public class JwtTokenUtil {


    @Autowired
    private ModelMapper modelMapper;


    public String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getFirst_name());

        claims.put("username", user.getFirst_name());

        claims.put("fireToken", user.getFiretoken());

        claims.put("role", user.getRole());

        claims.put("userId", user.getUserId());

        return Jwts.builder()
                .setClaims(claims)
                // .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public String getFireTokenFromToken(String token) {
        return (String) this.getAllClaimsFromToken(token).get("fireToken");
    }
    public Role getRoleFromToken(String token) {
        return Role.valueOf(this.getAllClaimsFromToken(token).get("role").toString());
    }

    public UserId getUserId(String token) {
        LinkedHashMap map =   (LinkedHashMap) this.getAllClaimsFromToken(token).get("userId");

        return new  UserId(map.get("user_key").toString(),map.get("user_pass").toString());
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
