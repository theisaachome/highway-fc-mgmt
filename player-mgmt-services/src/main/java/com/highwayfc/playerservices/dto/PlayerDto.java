package com.highwayfc.playerservices.dto;

import com.highwayfc.playerservices.domain.model.PlayerStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto{
    private Long id;
    private String playerIDNumber;
    private String fullName;
    private PlayerStatus playerStatus;
}
