package com.highwayfc.playerservices.repository;
import com.highwayfc.playerservices.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PlayerRepo extends JpaRepository<Player, Long> {
    Optional<Player> findByFullName(String name);
}
