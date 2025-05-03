package com.highwayfc.teamservices.domain.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class TeamStaff extends BaseEntity{
    private Long teamId;
    private Long staffId;

    private String position; // e.g., "Coach", "Physio"
    private LocalDate joinedDate;
}
