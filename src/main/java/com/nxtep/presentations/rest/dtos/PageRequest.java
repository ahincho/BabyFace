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
    @NotNull
    @Min(0)
    private Integer page = 0;
    @NotNull
    @Positive
    @Min(10)
    @Max(25)
    private Integer size = 10;
}
