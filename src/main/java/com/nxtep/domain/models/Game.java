package com.nxtep.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private Long id;
    private User user;
    private Integer hits;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
