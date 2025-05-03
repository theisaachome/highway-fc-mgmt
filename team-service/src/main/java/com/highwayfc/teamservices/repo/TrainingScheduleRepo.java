package com.highwayfc.teamservices.repo;

import com.highwayfc.teamservices.domain.model.TrainingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingScheduleRepo extends JpaRepository<TrainingSchedule, Long> {
}
