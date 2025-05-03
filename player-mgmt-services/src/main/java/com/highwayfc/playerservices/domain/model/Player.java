package com.highwayfc.playerservices.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player extends BaseEntity {

    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String playerID;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM-dd-yyyy")
    private LocalDate birthDate;
    @Column(nullable = false)
    private String nationality;
    @Column(nullable = false)
    private String position;

    private String profilePhotoUrl;

    @Enumerated(EnumType.STRING)
    private PlayerStatus status;

}
