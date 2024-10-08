package com.springboot.relation.data.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductResponseDto {

    private Long number;

    private String name;

    private int price;

    private int stock;

}