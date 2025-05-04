package com.highwayfc.playerservices.service;

import com.highwayfc.playerservices.domain.model.Player;
import com.highwayfc.playerservices.domain.model.PlayerStatus;
import com.highwayfc.playerservices.dto.APIResponse;
import com.highwayfc.playerservices.dto.PlayerDto;
import com.highwayfc.playerservices.dto.PlayerResponseDto;
import com.highwayfc.playerservices.dto.PlayerSearchRequest;

import java.util.List;

public interface PlayerService {

    Player getPlayer(Long playerId);
    Player createPlayer(Player player);
    Player updatePlayer(Long playerId,Player player);
    void deletePlayer(Long playerId);
    List<Player> getAllPlayers();
    Player getPlayersByName(String playerName);
    List<Player> searchPlayers(PlayerSearchRequest request);
    APIResponse<PlayerResponseDto> getAllPlayers(int pageNo, int pageSize, String sortBy, String sortDir);
    PlayerDto validatePlayer(Long playerId);
}
