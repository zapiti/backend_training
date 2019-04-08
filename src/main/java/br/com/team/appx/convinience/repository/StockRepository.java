package br.com.team.appx.convinience.repository;

import br.com.team.appx.convinience.model.Product;
import br.com.team.appx.convinience.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findAllByProduct(Product product);
}
