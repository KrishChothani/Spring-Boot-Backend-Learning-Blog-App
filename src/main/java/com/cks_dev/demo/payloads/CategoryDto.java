package com.cks_dev.demo.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryid;
    private String categoryTitle;
    private String categoryDescription;
}
