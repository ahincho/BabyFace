package com.nxtep.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Leader {
    private Integer id;
    private String nickname;
    private Integer games;
    private Integer points;
}
