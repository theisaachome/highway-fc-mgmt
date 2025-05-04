package com.highwayfc.teamservices.dto;

import com.highwayfc.teamservices.domain.model.PlayerStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponse {
   private Long id;
    private String playerIDNumber;
    private String fullName;
    private PlayerStatus playerStatus;
}
