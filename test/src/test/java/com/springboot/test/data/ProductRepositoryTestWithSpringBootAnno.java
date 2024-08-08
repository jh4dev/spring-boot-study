package com.springboot.test.data;

import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ProductRepositoryTestWithSpringBootAnno {

    @Autowired
    private ProductRepository productRepository;

    //저장 테스트
    @Test
    public void crudTest() {

        // -- Create --
        //Given
        Product givenProduct = Product.builder()
                .name("연필")
                .price(3000)
                .stock(200)
                .build();

        //When
        Product savedProduct = productRepository.save(givenProduct);

        //Then
        assertEquals(givenProduct.getName(), savedProduct.getName());
        assertEquals(givenProduct.getPrice(), savedProduct.getPrice());
        assertEquals(givenProduct.getStock(), savedProduct.getStock());

        // -- Read --
        //When
        Product selectedProduct = productRepository.findById(savedProduct.getNumber()).orElseThrow(RuntimeException::new);

        //Then
        assertEquals(givenProduct.getName(), savedProduct.getName());
        assertEquals(givenProduct.getPrice(), savedProduct.getPrice());
        assertEquals(givenProduct.getStock(), savedProduct.getStock());


        // -- Update --
        selectedProduct.setName("이름 변경");

        Product changedProduct = productRepository.save(selectedProduct);
        assertEquals(selectedProduct.getName(), changedProduct.getName());

        // -- Delete --
        productRepository.delete(changedProduct);
        assertFalse(productRepository.findById(selectedProduct.getNumber()).isPresent());
    }
}
