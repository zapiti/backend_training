package br.com.team.appx.convinience.service.product;

import br.com.team.appx.convinience.model.Product;
import br.com.team.appx.convinience.model.UserId;
import br.com.team.appx.convinience.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    Product saveProduct(Product product) {
        product.setCreatedOn(this.getCreatedOnById(product.getId()));
        product.setUpdatedOn(LocalDateTime.now());

        return this.productRepository.save(product);
    }

    private LocalDateTime getCreatedOnById(Long id) {
        if (id == null) {
            return LocalDateTime.now();
        }

        Product product = this.productRepository.findById(id)
                .orElse(null);

        if (product == null) {
            return LocalDateTime.now();
        }

        return product.getCreatedOn();
    }


    public ResponseEntity<Object> updateProduct(Product newProd, UserId userId) {

        Product product = this.productRepository.findByName(newProd.getName());

        if (product == null) {
            product = newProd;
        } else {
            if (newProd.getName() != null) {
                product.setName(newProd.getName());
            }
            if (newProd.getValidity() != null) {
                product.setValidity(newProd.getValidity());
            }
            if (newProd.getPrice() != null) {
                product.setPrice(newProd.getPrice());
            }

        }

        return ResponseEntity.ok(saveProduct(product));
    }
}
