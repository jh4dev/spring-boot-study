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

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUpTest() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void getProductTest() {
        Product givenProduct = Product.builder().number(123L).name("파인애플펜").price(4000).stock(200).build();

        Mockito.when(productRepository.findById(123L)).thenReturn(Optional.of(givenProduct));
        ProductResponseDto productResponseDto = productService.getProduct(123L);

        Assertions.assertEquals(productResponseDto.getNumber(), givenProduct.getNumber());
        Assertions.assertEquals(productResponseDto.getName(), givenProduct.getName());
        Assertions.assertEquals(productResponseDto.getPrice(), givenProduct.getPrice());
        Assertions.assertEquals(productResponseDto.getStock(), givenProduct.getStock());

        verify(productRepository).findById(123L);
    }

    @Test
    void saveProductTest() {
        Mockito.when(productRepository.save(any(Product.class))).then(returnsFirstArg());

        ProductResponseDto productResponseDto = productService.saveProduct(new ProductDto("연필", 1400, 420));

        Assertions.assertEquals(productResponseDto.getName(), "연필");
        Assertions.assertEquals(productResponseDto.getPrice(), 1400);
        Assertions.assertEquals(productResponseDto.getStock(), 420);

        verify(productRepository).save(any());
    }
}
