package com.springboot.relation.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class ProductDto {

    private String name;
    private int price;
    private int stock;
}
