package com.highwayfc.teamservices.repo;

import com.highwayfc.teamservices.domain.model.TeamPlayer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamPlayerRepo extends JpaRepository<TeamPlayer, Long> {
}
