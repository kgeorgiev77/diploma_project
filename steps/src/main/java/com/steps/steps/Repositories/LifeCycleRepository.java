package com.steps.steps.Repositories;
import ch.qos.logback.core.spi.LifeCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LifeCycleRepository extends JpaRepository<LifeCycle, Long> {
    Long getCurrentWeek();
    Long updateWeekNumber(Long weekNumber);
    Long getMaxLeaderboardId();
    Long updateLeaderboardId(Long id);
    Long getMaxTeamId();
    Long updateTeamId(Long id);
}
