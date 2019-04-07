package br.com.team.appx.convinience.repository;

import br.com.team.appx.convinience.model.Product;
import br.com.team.appx.convinience.model.User;
import br.com.team.appx.convinience.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
