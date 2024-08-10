package com.springboot.advjpa.data.repository;

import com.springboot.advjpa.data.entity.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryTestBase {

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    void createData(){
        Product p1 = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .build();

        Product p2 = Product.builder()
                .name("펜")
                .price(5000)
                .stock(300)
                .build();

        Product p3 = Product.builder()
                .name("펜")
                .price(500)
                .stock(50)
                .build();
//
//        Product savedP1 = productRepository.save(p1);
//        Product savedP2 = productRepository.save(p2);
//        Product savedP3 = productRepository.save(p3);
//
        List<Product> pList = new ArrayList<>();
        pList.add(p1);
        pList.add(p2);
        pList.add(p3);

        productRepository.saveAll(pList);
    }
}
