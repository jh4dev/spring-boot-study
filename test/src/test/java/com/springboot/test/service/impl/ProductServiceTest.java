package com.springboot.test.service.impl;

import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.data.entity.Product;
import com.springboot.test.data.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class ProductServiceTest {

    //Repository 를 Mock 객체로 주입
    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);

    //ProductService
    private ProductServiceImpl productService;

    //주입받은 Repository 를 초기화
    /**
     * JUnit 생명주기 어노테이션
     * BeforeEach   각 테스트 메서드가 실행되기 전에 호출되는 메서드
     * BeforeAll    테스트 시작 전, 1회 호출되는 메서드
     * AfterEach    각 테스트 메서드가 실행된 후에 호출되는 메서드
     * AfterAll     테스트 종료 후, 1회 호출되는 메서드
     * */
    @BeforeEach
    public void setUpTest() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void getProductTest() {

        //Given
        Product givenProduct = Product.builder().number(123L).name("파인애플펜").price(4000).stock(200).build();
        Mockito.when(productRepository.findById(123L)).thenReturn(Optional.of(givenProduct));

        //When
        ProductResponseDto productResponseDto = productService.getProduct(123L);

        //Then
        Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        verify(productRepository).findById(123L);
    }

    @Test
    void saveProductTest() {
        //Given
        Mockito.when(productRepository.save(any(Product.class))).then(returnsFirstArg());

        //When
        ProductResponseDto productResponseDto = productService.saveProduct(new ProductDto("연필", 1400, 420));

        //Then
        Assertions.assertEquals(productResponseDto.getName(), "연필");
        Assertions.assertEquals(productResponseDto.getPrice(), 1400);
        Assertions.assertEquals(productResponseDto.getStock(), 420);

        verify(productRepository).save(any());
    }
}
