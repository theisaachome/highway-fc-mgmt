package com.highwayfc.playerservices.service.impl;
import com.highwayfc.playerservices.domain.exception.ResourceNotFoundException;
import com.highwayfc.playerservices.domain.model.Player;
import com.highwayfc.playerservices.domain.model.PlayerStatus;
import com.highwayfc.playerservices.repository.PlayerRepo;
import com.highwayfc.playerservices.service.PlayerService;
import com.highwayfc.playerservices.utils.PlayerIDGenerator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepo playerRepo;
    private final PlayerIDGenerator playerIDGenerator;

    public PlayerServiceImpl(PlayerRepo playerRepo,PlayerIDGenerator playerIDGenerator) {
        this.playerRepo = playerRepo;
        this.playerIDGenerator=playerIDGenerator;
    }

    @Override
    public Player getPlayer(Long playerId) {
        return  playerRepo.findById(playerId).orElseThrow(()-> new ResourceNotFoundException("Player","ID",playerId));
    }

    @Override
    public Player createPlayer(Player player) {
        player.setPlayerID(playerIDGenerator.generatePlayerId(LocalDate.now(),"CFC"));
        return  playerRepo.save(player);
    }

    @Override
    public Player updatePlayer(Long playerId, Player player) {
        var playerFromDb= playerRepo.findById(playerId).orElseThrow(()-> new ResourceNotFoundException("Player","ID",playerId));
        return null;
    }

    @Override
    public void deletePlayer(Long playerId) {
        Player playerFrmDb = playerRepo.findById(playerId)
                .orElseThrow(()-> new ResourceNotFoundException("Player","ID",playerId));
//        playerFrmDb.setStatus(PlayerStatus.INACTIVE);
//        playerFrmDb.setActive(false);
        playerRepo.save(playerFrmDb);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepo.findAll();
    }

    @Override
    public Player getPlayersByName(String playerName) {
        return  playerRepo.findByFullName(playerName).orElseThrow(()->new ResourceNotFoundException("Player not found with "+playerName));
    }
}
