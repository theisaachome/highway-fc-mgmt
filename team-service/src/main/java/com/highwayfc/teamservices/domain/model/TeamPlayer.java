package com.highwayfc.teamservices.domain.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class TeamPlayer extends BaseEntity{

    private Long teamId;
    private Long playerId;

    private String role; // e.g., "Captain", "Vice-Captain"
    private LocalDate joinedDate;
}
