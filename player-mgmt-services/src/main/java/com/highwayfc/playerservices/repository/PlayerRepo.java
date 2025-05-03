package com.highwayfc.playerservices.repository;

import com.highwayfc.playerservices.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepo extends JpaRepository<Player, Long> {
}
