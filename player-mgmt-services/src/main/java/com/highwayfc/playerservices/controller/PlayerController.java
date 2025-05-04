package com.highwayfc.playerservices.controller;

import com.highwayfc.playerservices.domain.model.Player;
import com.highwayfc.playerservices.dto.APIResponse;
import com.highwayfc.playerservices.dto.PlayerDto;
import com.highwayfc.playerservices.dto.PlayerResponseDto;
import com.highwayfc.playerservices.dto.PlayerSearchRequest;
import com.highwayfc.playerservices.service.PlayerService;
import com.highwayfc.playerservices.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    //Player Registration
    @PostMapping
    public ResponseEntity<Player> registerPlayer(@RequestBody Player player) {
        var newPlayer = playerService.createPlayer(player);
        return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    }
    // Get Player Details

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayerDetails(@PathVariable("playerId") Long playerId) {
        var player = playerService.getPlayer(playerId);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }
    //Update Player Info
    @PutMapping
    public ResponseEntity<Player> updatePlayer(@PathVariable("playerId") Long playerId, @RequestBody Player player) {
        var newPlayer = playerService.updatePlayer(playerId, player);
        return new ResponseEntity<>(newPlayer, HttpStatus.OK);
    }
    //delete player
    @DeleteMapping("/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable("playerId") Long playerId) {
        playerService.deletePlayer(playerId);
        return new ResponseEntity<>("Player deleted", HttpStatus.OK);
    }

    //Get All Players by Status or Position
    @GetMapping
    public APIResponse<PlayerResponseDto> getAllPlayers(
            @RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false)String sortDir
    ){
      return playerService.getAllPlayers(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Player>> searchPlayers(@ModelAttribute PlayerSearchRequest request){
        var players = playerService.searchPlayers(request);
        return  new ResponseEntity<>(players, HttpStatus.OK);
    }
    //Upload Document
    //POST /players/{id}/documents

    //Check if player is valid/active
    //GET /api/players/validate?id={id}
    @GetMapping("/validate")
    public ResponseEntity<PlayerDto> validatePlayer(
            @RequestParam(value = "playerId") Long playerId
    ){
        var validPlayer = playerService.validatePlayer(playerId);
        return new ResponseEntity<>(validPlayer,HttpStatus.OK);
    }

}
