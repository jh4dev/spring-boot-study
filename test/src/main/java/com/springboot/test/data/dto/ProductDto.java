package com.springboot.test.data.dto;

import lombok.*;
import org.springframework.web.bind.annotation.RequestMapping;

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
