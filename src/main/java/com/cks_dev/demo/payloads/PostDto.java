package com.cks_dev.demo.payloads;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;


    private String imageName;

    private Date addDate;

    private CategoryDto category;

    private UserDto user;
}
