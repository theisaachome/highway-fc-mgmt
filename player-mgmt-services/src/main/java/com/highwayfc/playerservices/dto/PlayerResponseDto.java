package com.highwayfc.playerservices.dto;

import com.highwayfc.playerservices.domain.model.PlayerStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PlayerResponseDto(
        Long id,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String createdBy,
        String updatedBy,
        boolean active,
        String fullName,
        String playerID,
        LocalDate birthDate,
        String nationality,
        String position,
        String profilePhotoUrl,
        PlayerStatus status
) {
}
