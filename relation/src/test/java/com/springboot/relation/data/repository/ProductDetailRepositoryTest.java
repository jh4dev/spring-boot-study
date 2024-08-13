package com.springboot.relation.data.repository;

import com.springboot.relation.data.entity.Product;
import com.springboot.relation.data.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductDetailRepositoryTest {

    @Autowired
    ProductDetailRepository productDetailRepository;

    //QueryDsl 설정하지 않은 productRepository
    @Autowired
    ProductRepository productRepository;

    @Test
    void saveAndReadTest1() {

        Product product = new Product();
        product.setName("일대일");
        product.setPrice(2000);
        product.setStock(300);

        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDescription("1:1 관계 테스트 제품 1번");

        productDetailRepository.save(productDetail);

        System.out.println("Saved Product : " + productDetailRepository.findById(productDetail.getId()).get().getProduct());
        System.out.println("Saved Product Detail : " + productDetailRepository.findById(productDetail.getId()).get());
    }

}
