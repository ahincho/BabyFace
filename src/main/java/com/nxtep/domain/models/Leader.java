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
    private Integer rank;
    private String name;
    private Integer hits;
}
