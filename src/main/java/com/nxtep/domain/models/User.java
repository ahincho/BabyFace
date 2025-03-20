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
public class User {
    private Integer id;
    private String username;
    private String phone;
    private String photo;
    private String avatar;
    private Game game;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
