package com.highwayfc.playerservices.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Player extends BaseEntity {
    private String fullName;

    private LocalDate birthDate;

    private String nationality;

    private String position;

    private String profilePhotoUrl;

    @Enumerated(EnumType.STRING)
    private PlayerStatus status;
}
