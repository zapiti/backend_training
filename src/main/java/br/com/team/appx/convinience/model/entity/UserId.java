package br.com.team.appx.convinience.model.entity;

import br.com.team.appx.convinience.security.Criptografia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserId implements Serializable {



    private String user_key;
    private String user_pass;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId)) return false;

        UserId that = (UserId) o;

        return Objects.equals(getUser_key(), Criptografia.md5(that.getUser_key())) && Objects.equals(getUser_pass()
                , Criptografia.md5(that.getUser_pass()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(Criptografia.md5(getUser_key()), Criptografia.md5(getUser_key()));
    }
}
