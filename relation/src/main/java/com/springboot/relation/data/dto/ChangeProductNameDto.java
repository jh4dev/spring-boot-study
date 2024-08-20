package com.springboot.relation.data.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
public class ChangeProductNameDto {

    private final Long number;
    private final String name;
}