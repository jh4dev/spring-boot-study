package com.springboot.jpa.data.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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
