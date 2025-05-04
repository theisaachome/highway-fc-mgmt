package com.highwayfc.teamservices.services;

import com.highwayfc.teamservices.domain.model.Team;
import com.highwayfc.teamservices.dto.AssignPlayerRequest;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    // create team
    Team createTeam(Team team);
    // get team by id
    Team findById(Long id);
    // get teams by season
    List<Team> findBySeason(String season);
    List<Team> findAll();
    // update team
    Team updateTeam(Long teamId, Team team);
    void assignPlayerToTeam(AssignPlayerRequest request);
}
