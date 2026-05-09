package com.ws101.FulgarLim.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterUserDto {

    @NotBlank
    @Size(min = 3, max = 20)
    public String username;

    @NotBlank
    @Size(min = 4, max = 100)
    public String password;

    @NotBlank
    public String role;
}