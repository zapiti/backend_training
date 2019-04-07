package br.com.team.appx.convinience.model;

import lombok.Data;

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
    @NotNull
    @Column(nullable = false)
    private UserId userId;

    @Column(nullable = true)
    private  String firetoken;

    @Column(nullable = true)
    private String first_name;

    @Column(nullable = true)
    private String last_name;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private Long cpf;


    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private LocalDateTime updatedOn;
}
