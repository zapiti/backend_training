package br.com.team.appx.convinience.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;

    @Column(nullable = true,unique = true)
    private String name;

    @Column(nullable = true)
    private String validity;

    @Column(nullable = true)
    private Double price;

    @Column(nullable = true)
    private LocalDateTime createdOn;

    @Column(nullable = true)
    private LocalDateTime updatedOn;

    public Product orElse(Object o) {
        return null;
    }
}
