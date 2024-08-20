package com.springboot.valid.data.dto;

import com.springboot.valid.config.valid.annotation.Telephone;
import com.springboot.valid.data.valid.group.ValidationGroup1;
import com.springboot.valid.data.valid.group.ValidationGroup2;
import lombok.*;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidatedRequestDto {

    @NotBlank
    String name;

    @Email
    String email;

    //@Pattern(regexp = "01(?:0|1|[6~9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    @Telephone(groups = ValidationGroup1.class) //커스텀 유효성 검사 어노테이션
    String phoneNumber;

    //Group1
    @Min(value=20, groups = ValidationGroup1.class)
    @Max(value=40, groups = ValidationGroup1.class)
    int age;

    @Size(min = 0, max = 40)
    String description;

    //Group2
    @Positive(groups = ValidationGroup2.class)
    int count;

    @AssertTrue
    boolean booleanCheck;
}
