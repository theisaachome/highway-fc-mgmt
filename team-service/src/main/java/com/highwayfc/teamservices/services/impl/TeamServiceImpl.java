package com.highwayfc.teamservices.services.impl;
import com.highwayfc.teamservices.domain.model.Team;
import com.highwayfc.teamservices.repo.TeamRepo;
import com.highwayfc.teamservices.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepo teamRepo;
    @Override
    public Team createTeam(Team team) {
        var newTeam = teamRepo.save(team);
        return Optional.of(newTeam).orElseThrow(() -> new RuntimeException("something wrong"));
    }

    @Override
    public Team findById(Long id) {
        var resultTeam=  teamRepo.findById(id)
                  .orElseThrow(() -> new RuntimeException("something wrong"));
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
}
