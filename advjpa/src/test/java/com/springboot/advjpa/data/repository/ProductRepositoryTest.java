package com.springboot.advjpa.data.repository;

import com.springboot.advjpa.data.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class ProductRepositoryTest extends RepositoryTestBase{

    @Autowired
    ProductRepository productRepository;


    @Test
    void sortingAndPagingTest() {
        // 정렬
        List<Product> pl1 = productRepository.findByName("펜", Sort.by("price"));

        List<Product> pl2 = productRepository.findByName("펜", Sort.by(Order.asc("price")));

        List<Product> productList = productRepository.findByName("펜", Sort.by(Order.asc("price"), Order.desc("stock")));

        List<Product> pl4 = productRepository.findByName("펜", getCustomSort());

        // 페이징
        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0, 2));
        System.out.println(productPage.getContent());
    }

    private Sort getCustomSort() {
        return Sort.by(
                Order.asc("price"),
                Order.desc("stock")
        );
    }



}
