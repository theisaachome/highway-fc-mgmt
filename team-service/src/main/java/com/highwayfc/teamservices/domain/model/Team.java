package com.highwayfc.teamservices.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Team extends BaseEntity{
    @Column(unique=true,nullable=false)
    private String name;    // e.g., "Senior A"
    @Column(nullable=false)
    private String season;  // e.g., "2024/2025"
    private String description;
}
