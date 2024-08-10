package com.springboot.advjpa.data.repository.support;

import com.springboot.advjpa.data.entity.Product;
import com.springboot.advjpa.data.repository.RepositoryTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProductRepositoryTest extends RepositoryTestBase{

    @Autowired
    ProductRepository productRepository;

    @Test
    void findByNameTest() {
        List<Product> productList = productRepository.findByName("펜");

        for(Product product : productList) {
            System.out.println(product.toString());
        }
    }
}
