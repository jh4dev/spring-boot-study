package com.springboot.test.data;

import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTestWithProjectConfig {

    @Autowired
    private ProductRepository productRepository;

    //저장 테스트
    @Test
    public void saveTest() {
        Product product = new Product();
        product.setName("이름이야");
        product.setPrice(500000);
        product.setStock(2020);

        Product savedProduct = productRepository.save(product);

        Assertions.assertEquals(product.getName(), savedProduct.getName());
        Assertions.assertEquals(product.getPrice(), savedProduct.getPrice());
        Assertions.assertEquals(product.getStock(), savedProduct.getStock());
    }

    @Test
    void selectTest() {

        //Given
        Product product = new Product();
        product.setName("연필");
        product.setPrice(500000);
        product.setStock(2020);

        Product savedProduct = productRepository.saveAndFlush(product);

        //When
        Product foundProduct = productRepository.findById(savedProduct.getNumber()).get();

        //Then
        assertEquals(product.getName(), foundProduct.getName());
        assertEquals(product.getPrice(), foundProduct.getPrice());
        assertEquals(product.getStock(), foundProduct.getStock());
    }
}
