package com.steps.steps.Services;

import com.steps.steps.Entities.Leaderboard;
import com.steps.steps.Repositories.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LeaderboardService {
    private final LeaderboardRepository leaderboardRepository;

    @Autowired
    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    public Leaderboard getLeaderboardById(Long id) {
        Optional<Leaderboard> leaderboardOptional = leaderboardRepository.findById(id);
        return leaderboardOptional.orElse(null);
    }

    public Leaderboard createLeaderboard(Leaderboard leaderboard) {
        return leaderboardRepository.save(leaderboard);
    }

    public Leaderboard updateLeaderboard(Long id, Leaderboard leaderboard) {
        Optional<Leaderboard> leaderboardOptional = leaderboardRepository.findById(id);
        if (leaderboardOptional.isPresent()) {
            Leaderboard existingLeaderboard = leaderboardOptional.get();
            existingLeaderboard.setName(leaderboard.getName());
            return leaderboardRepository.save(existingLeaderboard);
        } else {
            return null;
        }
    }

    public boolean deleteLeaderboard(Long id) {
        Optional<Leaderboard> leaderboardOptional = leaderboardRepository.findById(id);
        if (leaderboardOptional.isPresent()) {
            leaderboardRepository.delete(leaderboardOptional.get());
            return true;
        } else {
            return false;
        }
    }
}