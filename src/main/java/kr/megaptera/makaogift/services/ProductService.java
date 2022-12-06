package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.ProductNotFound;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> products() {
        List<Product> products = productRepository.findAll();

        return products;
    }

    public Product product(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFound(id));

        return product;
    }
}
