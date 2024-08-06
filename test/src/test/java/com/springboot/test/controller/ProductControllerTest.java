package com.springboot.test.controller;

import com.google.gson.Gson;
import com.springboot.test.data.dto.ProductDto;
import com.springboot.test.data.dto.ProductResponseDto;
import com.springboot.test.service.impl.ProductServiceImpl;
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
        //Mock 객체에서 특정 메소드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어줌
        /**
         * 테스트 메서드의 주요 부분은 Mockito를 사용하여 productService.saveProduct 메서드를 모킹하고, MockMvc를 사용하여 /product 엔드포인트에 대한 POST 요청을 테스트한다.
         * 이 과정에서 중요한 두 가지 부분이 있다:
         *
         * given(productService.saveProduct(new ProductDto("pen", 5000, 2000))) 부분:
         * 여기서는 Mockito를 사용하여 productService.saveProduct 메서드가 특정 인자로 호출될 때 어떤 값을 반환해야 하는지를 저장한다..
         * 이 때, new ProductDto("pen", 5000, 2000) 객체가 saveProduct 메서드의 인자로 사용된다.
         *
         * verify(productService).saveProduct(new ProductDto("pen", 5000, 2000)) 부분:
         * 여기서는 Mockito의 verify 메서드를 사용하여 productService.saveProduct 메서드가 정확히 어떤 인자로 호출되었는지를 검증한다.
         * 이 때, 다시 new ProductDto("pen", 5000, 2000) 객체가 사용된다.
         *
         * 이 두 부분에서 new ProductDto("pen", 5000, 2000) 객체가 동일한 객체인지 비교하기 위해 equals 메서드를 호출하게 된다.
         * 기본적으로, Java의 Object 클래스에서 상속된 equals 메서드는 객체의 참조를 비교합니다. 따라서 두 객체가 같은 인스턴스가 아니라면, equals 메서드는 false를 반환하게 된다.
         *
         * 문제의 원인
         * ProductDto 클래스에 @EqualsAndHashCode 어노테이션이 없으면, 두 객체의 내용을 비교할 때 객체의 참조를 비교하게 된다.
         * 즉, new ProductDto("pen", 5000, 2000) 와 같은 내용을 가진 또 다른 new ProductDto("pen", 5000, 2000) 객체는 서로 다르다고 판단하게 되어 테스트가 실패하게 된다.
         *
         * 해결 방법
         * ProductDto 클래스에 @EqualsAndHashCode 어노테이션을 추가하면, 이 어노테이션이 클래스의 필드 값을 기준으로 equals와 hashCode 메서드를 생성하며
         * 같은 필드 값을 가진 두 객체는 equals 메서드에서 동일한 객체로 판단하게 된다. 이렇게 하면 Mockito의 given과 verify 부분에서 객체 비교가 제대로 이루어진다.
         *
         * 요약
         * given과 verify 부분에서 new ProductDto("pen", 5000, 2000) 객체를 비교할 때, 내용이 동일한지 확인하려면 @EqualsAndHashCode가 필요!.
         * @EqualsAndHashCode를 추가하면 객체의 참조가 아닌 필드 값을 기준으로 비교하게 되어 테스트가 정상적으로 작동!.
         * */

        given(productService.saveProduct(new ProductDto("pen", 5000, 2000)))
                .willReturn(new ProductResponseDto(12315L, "pen", 5000, 2000));

        ProductDto productDto = ProductDto.builder()
                .name("pen")
                .price(5000)
                .stock(2000)
                .build();

        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        mockMvc.perform(
                        post("/product")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").exists())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.price").exists())
                .andExpect(jsonPath("$.stock").exists())
                .andDo(print());

        verify(productService).saveProduct(new ProductDto("pen", 5000, 2000));
    }
}
