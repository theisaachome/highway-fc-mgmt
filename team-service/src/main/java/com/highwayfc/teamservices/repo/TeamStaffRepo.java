package com.highwayfc.teamservices.repo;
import com.highwayfc.teamservices.domain.model.TeamStaff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamStaffRepo extends JpaRepository<TeamStaff, Long> {
}
