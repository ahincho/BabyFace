package com.nxtep.presentations.rest.dtos;

import com.nxtep.presentations.rest.annotations.MultipleOf;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameCreateRequest {
    @NotNull(message = "El id del usuario no puede estar vacío")
    @Positive(message = "El id del usuario debe ser un número entero positivo")
    private Integer userId;
    @NotNull(message = "Los puntos no pueden estar vacíos")
    @Positive(message = "Los puntos deben ser un número entero positivo")
    @MultipleOf(value = 50, message = "Los puntos deben ser múltiplos de 50")
    private Integer points;
}
