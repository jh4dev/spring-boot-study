package com.springboot.jpa.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDto {

    @Schema(name = "number", description = "제품을 식별하는 Key 값", example = "123")
    private Long number;

    @Schema(name = "name", description = "제품의 이름", example = "연필")
    private String name;

    @Schema(name = "price", description = "제품의 가격", example = "3000")
    private int price;

    @Schema(name = "stock", description = "제품의 재고 수량", example = "200")
    private int stock;
}
