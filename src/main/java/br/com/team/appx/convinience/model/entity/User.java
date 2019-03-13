package br.com.team.appx.convinience.model.entity;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_users")
public class User {
    @EmbeddedId
    private UserId id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private LocalDateTime updatedOn;
}
