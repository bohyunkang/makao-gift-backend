package kr.megaptera.makaogift.services;

import kr.megaptera.makaogift.exceptions.ProductNotFound;
import kr.megaptera.makaogift.models.Product;
import kr.megaptera.makaogift.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> products(Integer page, Integer size) {
        Sort sort = Sort.by("id");

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        return productRepository.findAll(pageable);
    }

    public Product product(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFound(id));

        return product;
    }
}
