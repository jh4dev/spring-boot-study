package com.springboot.service.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.dao.impl.ProductDAOImpl;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        Product product = productDAO.selectProduct(number);

        return ProductResponseDto.builder()
                .number(product.getNumber())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = Product.builder()
            .name(productDto.getName())
            .price(productDto.getPrice())
            .stock(productDto.getStock())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

        Product savedProduct = productDAO.insertProduct(product);

        return ProductResponseDto.builder()
                .number(savedProduct.getNumber())
                .name(savedProduct.getName())
                .price(savedProduct.getPrice())
                .stock(savedProduct.getStock())
                .build();
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {

        Product changedProduct = productDAO.updateProductName(number, name);

        return ProductResponseDto.builder()
                .number(changedProduct.getNumber())
                .name(changedProduct.getName())
                .price(changedProduct.getPrice())
                .stock(changedProduct.getStock())
                .build();
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }
}
