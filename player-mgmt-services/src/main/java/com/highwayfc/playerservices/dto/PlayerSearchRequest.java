package com.highwayfc.playerservices.dto;
import com.highwayfc.playerservices.domain.model.PlayerStatus;
import java.time.LocalDate;
public record PlayerSearchRequest(String fullName,
                                  String nationality,
                                  PlayerStatus status,
                                  String position,
                                  LocalDate birthDate
                                  ) {
}
