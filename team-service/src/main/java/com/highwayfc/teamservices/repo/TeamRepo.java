package com.highwayfc.teamservices.repo;

import com.highwayfc.teamservices.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepo extends JpaRepository<Team, Long> {

    List<Team> findByName(String name);
    List<Team> findBySeason(String seasonName);
}
