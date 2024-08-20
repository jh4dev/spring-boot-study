package com.springboot.valid.service.impl;

import com.springboot.valid.data.dto.ProductDto;
import com.springboot.valid.data.dto.ProductResponseDto;
import com.springboot.valid.data.entity.Product;
import com.springboot.valid.data.repository.ProductRepository;
import com.springboot.valid.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        log.info("[getProduct()] product number = {}", number);
        Optional<Product> op = productRepository.findById(number);
        Product product = op.get();

        log.info("[getProduct()] find product number : {}, name : {}", product.getNumber(), product.getName());

        return ProductResponseDto.builder()
                .number(product.getNumber())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        log.info("[saveProduct()] productDto : {}", productDto.toString());

        Product product = Product.builder()
            .name(productDto.getName())
            .price(productDto.getPrice())
            .stock(productDto.getStock())
            .build();

        Product savedProduct = productRepository.save(product);
        log.info("[saveProduct()] savedProduct : {}", savedProduct.toString());

        return ProductResponseDto.builder()
                .number(savedProduct.getNumber())
                .name(savedProduct.getName())
                .price(savedProduct.getPrice())
                .stock(savedProduct.getStock())
                .build();
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {

        Product foundProduct = productRepository.findById(number).get();
        foundProduct.setName(name);

        Product changedProduct = productRepository.save(foundProduct);

        return ProductResponseDto.builder()
                .number(changedProduct.getNumber())
                .name(changedProduct.getName())
                .price(changedProduct.getPrice())
                .stock(changedProduct.getStock())
                .build();
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productRepository.deleteById(number);
    }
}
