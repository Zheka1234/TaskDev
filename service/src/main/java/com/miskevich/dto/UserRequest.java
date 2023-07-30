package com.miskevich.dto;

import com.miskevich.domain.SystemRoles;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserRequest {

    @Size(max = 40)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String surname;

    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String firstname;

    @Size(max = 40)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String lastname;

    @Size(max = 50)
    @Email
    private String email;

    @NotNull
    private SystemRoles role;
}
