package com.habitat.dto.user.request;

import com.habitat.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@FieldMatch(first = "password", second = "repeatPassword", message = "The password fields must match")
@Data
public class UserRegistrationRequestDto {
    @NotBlank
    @Size(min = 4, max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @NotBlank
    @Size(min = 6, max = 100)
    private String repeatPassword;

    @NotBlank
    @Size(min = 6, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 6, max = 100)
    private String lastName;

}
