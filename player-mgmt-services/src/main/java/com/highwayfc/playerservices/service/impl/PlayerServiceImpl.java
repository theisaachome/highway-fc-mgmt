package com.highwayfc.playerservices.service.impl;
import com.highwayfc.playerservices.domain.exception.ResourceNotFoundException;
import com.highwayfc.playerservices.domain.model.Player;
import com.highwayfc.playerservices.domain.model.PlayerStatus;
import com.highwayfc.playerservices.dto.APIResponse;
import com.highwayfc.playerservices.dto.PlayerDto;
import com.highwayfc.playerservices.dto.PlayerResponseDto;
import com.highwayfc.playerservices.dto.PlayerSearchRequest;
import com.highwayfc.playerservices.repository.PlayerRepo;
import com.highwayfc.playerservices.repository.PlayerSpecification;
import com.highwayfc.playerservices.service.PlayerService;
import com.highwayfc.playerservices.utils.PlayerIDGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        playerFrmDb.setStatus(PlayerStatus.INACTIVE);
        playerFrmDb.setActive(false);
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
    @Override
    public List<Player> searchPlayers(PlayerSearchRequest request) {
        Specification<Player> spec = Specification.where(PlayerSpecification.fullNameContains(request.fullName()))
                .and(PlayerSpecification.hasNationality(request.nationality()))
                .and(PlayerSpecification.hasStatus(request.status()));
        return playerRepo.findAll(spec);
    }

    @Override
    public APIResponse<PlayerResponseDto> getAllPlayers(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort =sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Player> players = playerRepo.findAll(pageable);
        List<Player> playerList=players.getContent();
        var playersDto =playerList.stream()
                .map(data->{
                    return new PlayerResponseDto(
                            data.getId(),
                            data.getCreatedAt(),
                            data.getUpdatedAt(),
                            data.getCreatedBy(),
                            data.getUpdatedBy(),
                            data.isActive(),
                            data.getFullName(),
                            data.getPlayerID(),
                            data.getBirthDate(),
                            data.getNationality(),
                            data.getPosition(),
                            data.getProfilePhotoUrl(),
                            data.getStatus());
                })
                .collect(Collectors.toList());
        return new APIResponse<>(
                playersDto,
                players.getNumber(),
                players.getSize(),
                players.getTotalElements(),
                players.getTotalPages(),
                players.isLast()
        );
    }

    @Override
    public PlayerDto validatePlayer(Long playerId) {
        // find active player
        var activePlayer = playerRepo.findById(playerId).orElseThrow(()-> new ResourceNotFoundException("Player","ID",playerId));
            return new PlayerDto(activePlayer.getId(),
                    activePlayer.getPlayerID(),
                    activePlayer.getFullName(),
                    activePlayer.getStatus());

    }
}
