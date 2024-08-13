package com.springboot.relation.data.repository;

import com.springboot.relation.data.entity.Category;
import com.springboot.relation.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void relationTest() {

        Product p1 = new Product();
        p1.setName("펜");
        p1.setPrice(30000);
        p1.setStock(2000);

        productRepository.save(p1);

        Category category = new Category();
        category.setCode("C1");
        category.setName("필기구");
        category.getProducts().add(p1);

        categoryRepository.save(category);

        List<Product> products = categoryRepository.findById(1L).get().getProducts();

        for(Product p : products) {
            System.out.println(p.toString());
        }
    }
}
