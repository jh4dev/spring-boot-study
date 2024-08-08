package com.springboot.advjpa.data.repository;

import com.springboot.advjpa.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void sortingAndPagingTest() {

        Product p1 = Product.builder()
                .name("펜")
                .price(1000)
                .stock(100)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product p2 = Product.builder()
                .name("펜")
                .price(5000)
                .stock(300)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product p3 = Product.builder()
                .name("펜")
                .price(500)
                .stock(50)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Product savedP1 = productRepository.save(p1);
        Product savedP2 = productRepository.save(p2);
        Product savedP3 = productRepository.save(p3);

        // 정렬
        List<Product> pl1 = productRepository.findByName("펜", Sort.by("price"));
        System.out.println(pl1);

        List<Product> pl2 = productRepository.findByName("펜", Sort.by(Order.asc("price")));
        System.out.println(pl2);

        List<Product> pl3 = productRepository.findByName("펜", Sort.by(Order.asc("price"), Order.desc("stock")));
        System.out.println(pl3);

        List<Product> pl4 = productRepository.findByName("펜", getCustomSort());
        System.out.println(pl4);

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
