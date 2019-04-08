package br.com.team.appx.convinience.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_stock")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToMany
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;


    @Column(nullable = true)
    private LocalDateTime updatedOn;


}
