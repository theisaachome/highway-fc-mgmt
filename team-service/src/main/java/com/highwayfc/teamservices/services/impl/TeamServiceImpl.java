package com.highwayfc.teamservices.services.impl;
import com.highwayfc.teamservices.domain.exception.ResourceNotFoundException;
import com.highwayfc.teamservices.domain.model.Team;
import com.highwayfc.teamservices.domain.model.TeamPlayer;
import com.highwayfc.teamservices.dto.AssignPlayerRequest;
import com.highwayfc.teamservices.dto.PlayerResponse;
import com.highwayfc.teamservices.repo.TeamPlayerRepo;
import com.highwayfc.teamservices.repo.TeamRepo;
import com.highwayfc.teamservices.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepo teamRepo;

    private final TeamPlayerRepo teamPlayerRepo;

    private final RestTemplate restTemplate;

    @Value("${player-service.url}")
    private String playerServiceUrl;

    public TeamServiceImpl(TeamPlayerRepo teamPlayerRepo, RestTemplate restTemplate) {
        this.teamPlayerRepo = teamPlayerRepo;
        this.restTemplate = restTemplate;
    }

    @Override
    public Team createTeam(Team team) {
        return  teamRepo.save(team);
    }

    @Override
    public Team findById(Long id) {
        var resultTeam=  teamRepo.findById(id)
                  .orElseThrow(() -> new ResourceNotFoundException("something wrong"));
        return resultTeam;
    }

    @Override
    public List<Team> findBySeason(String season) {
        return teamRepo.findBySeason(season);
    }

    @Override
    public List<Team> findAll() {
        return teamRepo.findAll();
    }

    @Override
    public Team updateTeam(Long teamId, Team team) {
        // find with id from database
        var existingData = teamRepo.findById(teamId).orElseThrow(() -> new RuntimeException("something wrong"));
        existingData.setSeason(team.getSeason());
        existingData.setName(team.getName());
        existingData.setDescription(team.getDescription());
       return teamRepo.save(existingData);
    }

    @Override
    public void assignPlayerToTeam(AssignPlayerRequest request) {

        //  check if team is exist
        var teamToAssign = teamRepo.findById(request.teamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team","ID", request.teamId()));
        // 1. Validate player exists by calling Player Service
        String url = playerServiceUrl + "?playerId=" + request.playerId();

        ResponseEntity<PlayerResponse> responseEntity = restTemplate.getForEntity(url, PlayerResponse.class);
        var tem=responseEntity.getBody();
        if (!responseEntity.getStatusCode().is2xxSuccessful() || responseEntity.getBody() == null) {
            throw new RuntimeException("Player not found in Player Service");
        }else{
            var responsePlayer= responseEntity.getBody();
            var teamPlayer = new TeamPlayer();
            teamPlayer.setTeamId(teamToAssign.getId());
            teamPlayer.setPlayerId(request.playerId());
            teamPlayer.setPlayerIDNumber(responsePlayer.getPlayerIDNumber());
            teamPlayer.setPlayerName(responsePlayer.getFullName());
            teamPlayer.setRole("Player");
            teamPlayer.setJoinedDate(LocalDate.now());
//            teamPlayer.setActive(responsePlayer.getPlayerStatus());
            teamPlayerRepo.save(teamPlayer);
        }


    }
}
