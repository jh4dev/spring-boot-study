package com.springboot.advjpa.service;

import com.springboot.advjpa.data.dto.ProductDto;
import com.springboot.advjpa.data.dto.ProductResponseDto;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto);

    ProductResponseDto changeProductName(Long number, String name) throws Exception;

    void deleteProduct(Long number) throws Exception;

}
