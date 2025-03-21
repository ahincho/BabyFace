package com.nxtep.presentations.rest.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class PageRequest {
    @NotNull(message = "La página no puede estar vacía")
    @Min(value = 0, message = "La página debe ser un número entero mayor o igual a 0")
    private Integer page = 0;
    @NotNull(message = "El tamaño de página no puede estar vacío")
    @Positive(message = "El tamaño de página debe ser un número entero positivo")
    @Min(value = 10, message = "El tamaño de página debe ser al menos 10")
    @Max(value = 25, message = "El tamaño de página no puede ser mayor a 25")
    private Integer size = 10;
}
