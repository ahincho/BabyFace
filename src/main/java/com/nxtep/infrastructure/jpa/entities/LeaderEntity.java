package com.nxtep.infrastructure.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "leaders")
@Entity(name = "leader")
public class LeaderEntity {
    @Id
    private Integer id;
    private String name;
    private Integer points;
    private Integer rank;
}
