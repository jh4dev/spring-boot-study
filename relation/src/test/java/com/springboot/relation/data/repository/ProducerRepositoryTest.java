package com.springboot.relation.data.repository;

import com.springboot.relation.data.entity.Producer;
import com.springboot.relation.data.entity.Product;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;

@SpringBootTest
public class ProducerRepositoryTest {

    @Autowired
    ProducerRepository producerRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void manyToManyTest() {

        Product product1 = productRepository.save(Product.builder().name("Macbook Air").price(1500000).stock(4000).build());
        Product product2 = productRepository.save(Product.builder().name("Macbook Pro").price(2500000).stock(3000).build());
        Product product3 = productRepository.save(Product.builder().name("Macbook Max").price(3500000).stock(2000).build());

        Producer producer1 = producerRepository.save(Producer.builder().code("vp").name("Vietnam Plant").products(new ArrayList<>()).build());
        Producer producer2 = producerRepository.save(Producer.builder().code("cp").name("China Plant").products(new ArrayList<>()).build());

        producer1.addProduct(product1);
        producer1.addProduct(product2);

        producer2.addProduct(product2);
        producer2.addProduct(product3);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));

//        System.out.println(producerRepository.findById(1L).get().getProducts());
    }

//    private Product saveProduct(String name, Integer price, Integer stock) {
//        return productRepository.save(Product.builder().name(name).price(price).stock(stock).build());
//    }
//
//    private Producer saveProducer(String name) {
//        return producerRepository.save(Producer.builder().name(name).products(new ArrayList<>()).build());
//    }

    @Test
    void manyToManyTest2() {

        Product product1 = productRepository.save(Product.builder().name("Macbook Air").price(1500000).stock(4000).build());
        Product product2 = productRepository.save(Product.builder().name("Macbook Pro").price(2500000).stock(3000).build());
        Product product3 = productRepository.save(Product.builder().name("Macbook Max").price(3500000).stock(2000).build());

        Producer producer1 = producerRepository.save(Producer.builder().code("vp").name("Vietnam Plant").products(new ArrayList<>()).build());
        Producer producer2 = producerRepository.save(Producer.builder().code("cp").name("China Plant").products(new ArrayList<>()).build());

    }
}
