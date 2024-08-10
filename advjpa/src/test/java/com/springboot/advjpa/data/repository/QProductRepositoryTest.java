package com.springboot.advjpa.data.repository;

import com.querydsl.core.types.Predicate;
import com.springboot.advjpa.data.entity.Product;
import com.springboot.advjpa.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class QProductRepositoryTest extends RepositoryTestBase {

    @Autowired
    QProductRepository qProductRepository;

    @Test
    void predicateFindOneTest() {
        Predicate predicate = QProduct.product.name.containsIgnoreCase("펜")
                .and(QProduct.product.price.between(1000, 2500));

        Optional<Product> foundProduct = qProductRepository.findOne(predicate);

        if(foundProduct.isPresent()) {
            Product product = foundProduct.get();

            System.out.println(product);
        }
    }

    @Test
    void predicateFindAllTest() {
        QProduct qProduct = QProduct.product;

        Iterable<Product> productList = qProductRepository.findAll(
                qProduct.name.contains("펜")
                        .and(qProduct.price.between(500, 1500))
        );

        for(Product p : productList) {
            System.out.println(p.toString());
        }
    }
}
