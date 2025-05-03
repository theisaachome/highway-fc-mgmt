package com.highwayfc.playerservices.controller;

import com.highwayfc.playerservices.domain.model.Player;
import com.highwayfc.playerservices.service.PlayerService;
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
    public ResponseEntity<List<Player>> getPlayers(@RequestParam(required = false) String position){
      var players= playerService.getAllPlayers();
      return new ResponseEntity<>(players, HttpStatus.OK);
    }
    //Upload Document
    //POST /players/{id}/documents
}
