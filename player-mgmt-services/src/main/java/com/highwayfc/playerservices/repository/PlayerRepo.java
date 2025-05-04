package com.highwayfc.playerservices.repository;
import com.highwayfc.playerservices.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface PlayerRepo extends JpaRepository<Player, Long> , JpaSpecificationExecutor<Player> {
    Optional<Player> findByFullName(String name);
}
