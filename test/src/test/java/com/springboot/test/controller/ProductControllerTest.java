package com.springboot.test.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //인터페이스로는 안되는지 확인 해보자.
    @MockBean
    ProductServiceImpl productService;

    @Test
    @DisplayName("MockMvc 를 통한 Product 데이터 조회 테스트")
    void getProductTest() throws Exception {

        given(productService.getProduct(123L)).willReturn(new ProductResponseDto(123L, "pen", 5000, 200));

        String productId = "123";

        mockMvc.perform(get("/product?number=" + productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).getProduct(123L);

    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {

        //Mock 객체에서 특정 메서드가 실행되는 경우, 실제 Return 을 줄 수 없기 때문에 아래와 같이 가정 사항을 만든다.
        given(productService.saveProduct(new ProductDto("pencil", 3000, 200)))
                .willReturn(new ProductResponseDto(12315L, "pencil", 3000, 200));

        ProductDto productDto = ProductDto.builder().name("pencil").price(3000).stock(200).build();

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(
                post("/product")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath(("$.number")).exists())
                .andExpect(jsonPath(("$.name")).exists())
                .andExpect(jsonPath(("$.price")).exists())
                .andExpect(jsonPath(("$.stock")).exists())
                .andDo(print());

        verify(productService).saveProduct(new ProductDto("pencil", 3000, 200));
    }
}
