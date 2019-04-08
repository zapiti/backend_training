package br.com.team.appx.convinience.service.stock;

import br.com.team.appx.convinience.model.Company;
import br.com.team.appx.convinience.model.Product;
import br.com.team.appx.convinience.model.Stock;
import br.com.team.appx.convinience.model.UserId;
import br.com.team.appx.convinience.repository.StockRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockService {

    @Autowired
    private StockRepository stokeRepository;

    private Stock saveStock(Stock stock) {
        stock.setUpdatedOn(LocalDateTime.now());

        return this.stokeRepository.save(stock);
    }

//    private LocalDateTime getCreatedOnById(Long id) {
//        if (id == null) {
//            return LocalDateTime.now();
//        }
//
//        Stock product = this.stokeRepository.findById(id)
//                .orElse(null);
//
//        if (product == null) {
//            return LocalDateTime.now();
//        }
//
//        return product.getCreatedOn();
//    }


    public ResponseEntity<Object> updateProduct(Product product, Long quantity, Company company) {

        Stock stock = this.stokeRepository.findAllByProduct(product);

        if (stock == null) {
            stock = new Stock();
            stock.setCompany(company);
            stock.setProduct(product);
        }
        stock.setQuantity(quantity);

        return ResponseEntity.ok(saveStock(stock));
    }
}
