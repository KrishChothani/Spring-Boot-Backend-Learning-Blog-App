package com.cks_dev.demo.payloads;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryid;

    @NotEmpty
    private String categoryTitle;

    @NotEmpty
    private String categoryDescription;
}
