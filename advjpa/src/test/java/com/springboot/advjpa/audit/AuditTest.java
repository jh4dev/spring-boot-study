package com.springboot.advjpa.audit;

import com.springboot.advjpa.data.entity.Product;
import com.springboot.advjpa.data.repository.support.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuditTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void auditTest(){

        Product product = Product.builder()
                .name("테스트").price(3000).stock(20).build();
        System.out.println(product.getCreatedAt());
        System.out.println(product.getUpdatedAt());

        Product savedProduct = productRepository.save(product);


        System.out.println(savedProduct.getCreatedAt());
        System.out.println(savedProduct.getUpdatedAt());

    }
}
