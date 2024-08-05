package com.springboot.jpa.controller;

import com.springboot.jpa.data.dto.ChangeProductNameDto;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
            summary = "Get a Product by product number",
            description = "Returns a single product based on the provided number",
            tags = {"Products"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ProductResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Product not found")
            }
    )
    @GetMapping
    public ResponseEntity<ProductResponseDto> getProduct(@Parameter(description = "제품 번호", required = true, example = "1") Long number) {
        ProductResponseDto productResponseDto = productService.getProduct(number);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {

        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PutMapping
    public ResponseEntity<ProductResponseDto> changeProduct(@RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
        ProductResponseDto productResponseDto
                = productService.changeProductName(changeProductNameDto.getNumber(), changeProductNameDto.getName());

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body("삭제 완료");
    }

}
