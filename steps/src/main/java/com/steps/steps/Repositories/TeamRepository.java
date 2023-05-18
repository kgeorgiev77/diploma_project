package com.steps.steps.Repositories;

import com.steps.steps.Entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> getAllTeams();
    List<Team> findByLeaderboardId(Long leaderboardId);
    List<Team> findByWeekNumber(Long weekNumber);
    Optional<Team> findById(Long id);
}
