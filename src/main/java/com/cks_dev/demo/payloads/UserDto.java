package com.cks_dev.demo.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {
    
    private int id;

    @NotEmpty
    @Size(min=4, message="User name must be at least 4 characters")
    private String name;

    @Email(message="Email address is not valid")
    private String email;

    @NotEmpty
    @Size(min=4, max=10,message="Password must be minimum of 4 chars and maximum of 10 chars")
    private String password;

    @NotEmpty
    private String about;

}
