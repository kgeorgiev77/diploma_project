package com.steps.steps.Repositories;

import com.steps.steps.Entities.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderboardRepository extends JpaRepository<Leaderboard, Long> {
    // Add custom repository methods if needed
}