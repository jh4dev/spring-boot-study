package com.springboot.valid.data.dao.impl;

import com.springboot.valid.data.dao.ProductDAO;
import com.springboot.valid.data.entity.Product;
import com.springboot.valid.data.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private ProductRepository productRepository;

    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {

        Optional<Product> selectedProduct = productRepository.findById(number);

        return selectedProduct.orElseGet(Product::new);
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        Product updatedProduct;
        if(selectedProduct.isPresent()) {
            Product p = selectedProduct.get();

            p.setName(name);
            p.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(p);
        } else {
            throw new Exception();
        }
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

        Optional<Product> selectedProduct = productRepository.findById(number);

        if(selectedProduct.isPresent()) {
            Product p = selectedProduct.get();

            productRepository.delete(p);
        } else {
            throw new Exception();
        }
    }
}
