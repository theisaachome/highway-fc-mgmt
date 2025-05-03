package com.highwayfc.teamservices.controller;

import com.highwayfc.teamservices.domain.model.Team;
import com.highwayfc.teamservices.services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    //  Create team
    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team){
        var resultTeam = teamService.createTeam(team);
        return  new ResponseEntity<>(resultTeam,HttpStatus.CREATED);
    }
    //List teams for a season
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeamsForSeason(
            @RequestParam(defaultValue = "2024/2025",required = false) String season) {
        if(season == null){
          return new ResponseEntity<>(teamService.findAll(),HttpStatus.OK);
        }
        return new ResponseEntity<>(teamService.findBySeason(season), HttpStatus.OK);
    }

    // View team details
    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable("teamId") Long teamId,@RequestBody Team team) {
        return new ResponseEntity<>(teamService.updateTeam(teamId,team), HttpStatus.OK);
    }

//    Assignments
//    POST /teams/{id}/players → Assign players
//
//    POST /teams/{id}/staff → Assign staff
//
//    GET /teams/{id}/players → Get all players
//
//    GET /teams/{id}/staff → Get all staff

//    Training Schedule
//    POST /teams/{id}/training-schedules → Add schedule
//
//    GET /teams/{id}/training-schedules → Get all schedules
//
//    PUT /training-schedules/{id} → Update schedule
//
//    DELETE /training-schedules/{id} → Delete schedule
}
