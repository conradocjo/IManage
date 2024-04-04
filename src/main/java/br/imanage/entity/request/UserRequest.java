package br.imanage.entity.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class UserRequest {

    @NotBlank
    @NotNull
    private String user;

    @NotNull
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;


    @NotBlank
    @NotNull
    @Size(min = 10, max = 14)
    private String name;
}
