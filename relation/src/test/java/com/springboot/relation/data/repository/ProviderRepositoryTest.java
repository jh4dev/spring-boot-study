package com.springboot.relation.data.repository;

import com.springboot.relation.data.entity.Product;
import com.springboot.relation.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.ProviderException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    void oneToManyTest() {

        Provider provider = new Provider();
        provider.setName("공급사 A");

        providerRepository.save(provider);

        Product p1 = new Product();
        p1.setName("A사 제품1");
        p1.setPrice(30000);
        p1.setStock(200);
        p1.setProvider(provider);

        Product p2 = new Product();
        p2.setName("A사 제품2");
        p2.setPrice(40000);
        p2.setStock(400);
        p2.setProvider(provider);

        List<Product> productList = new ArrayList<>();
        productList.add(p1);
        productList.add(p2);

        productRepository.saveAll(productList);

        List<Product> savedProducts = productRepository.findByProviderId(provider.getId());
        for(Product p : savedProducts) {
            System.out.println(p.toString());
        }
    }

    @Test
    void oneToManyTest2() {

        Provider provider = new Provider();
        provider.setName("Samsung");

        providerRepository.save(provider);

        Product p1 = new Product();
        p1.setName("갤럭시 Z 플립");
        p1.setPrice(1500000);
        p1.setStock(20000);
        p1.setProvider(provider);

        Product p2 = new Product();
        p2.setName("갤럭시 Z 폴드");
        p2.setPrice(2000000);
        p2.setStock(14000);
        p2.setProvider(provider);

        Product p3 = new Product();
        p3.setName("갤럭시 S");
        p3.setPrice(1000000);
        p3.setStock(10000);
        p3.setProvider(provider);

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);

        List<Product> productList = providerRepository.findById(provider.getId()).get().getProductList();

        for(Product p : productList){
            System.out.println(p.toString());
        }
    }
}
