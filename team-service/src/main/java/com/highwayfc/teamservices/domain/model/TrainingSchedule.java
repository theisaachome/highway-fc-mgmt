package com.highwayfc.teamservices.domain.model;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalTime;



@Entity
@Getter
@Setter
public class TrainingSchedule extends BaseEntity{
    private Long teamId;

    private LocalDate date;
    private LocalTime time;
    private String location;
    private String focus; // e.g., "Fitness", "Tactical"
}
