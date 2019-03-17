package br.com.team.appx.convinience.model.entity;

import lombok.Data;
import lombok.Generated;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Role role;

    @Column(nullable = true)
    private  String firetoken;

    @Column(nullable = true)
    private String first_name;

    @Column(nullable = true)
    private String last_name;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String telephone;

    @Column(nullable = true)
    private Long cpf;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private UserId userId;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private LocalDateTime updatedOn;
}
