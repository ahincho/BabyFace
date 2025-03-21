package com.nxtep.presentations.rest.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    @NotBlank(message = "El nombre del usuario no puede estar vacío")
    @Size(min = 2, max = 32, message = "El nombre del usuario debe tener entre 2 y 32 caracteres")
    private String username;
    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Size(min = 9, max = 9, message = "El número de teléfono debe tener exactamente 9 dígitos")
    @Pattern(regexp = "\\d{9}", message = "El número de teléfono solo debe contener números")
    private String phone;
}
