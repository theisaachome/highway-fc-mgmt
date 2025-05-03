package com.highwayfc.playerservices.service;

import com.highwayfc.playerservices.domain.model.Player;

import java.util.List;

public interface PlayerService {

    Player getPlayer(Long playerId);
    Player createPlayer(Player player);
    Player updatePlayer(Long playerId,Player player);
    void deletePlayer(Long playerId);
    List<Player> getAllPlayers();
    Player getPlayersByName(String playerName);
}
