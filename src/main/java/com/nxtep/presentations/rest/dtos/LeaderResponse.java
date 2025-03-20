package com.nxtep.presentations.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LeaderResponse {
    private Integer id;
    private Integer rank;
    private String name;
    private Integer points;
}
