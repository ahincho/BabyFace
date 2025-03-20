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
    @NotNull
    private Integer userId;
    @NotNull
    @Positive
    @MultipleOf(50)
    private Integer points;
}
