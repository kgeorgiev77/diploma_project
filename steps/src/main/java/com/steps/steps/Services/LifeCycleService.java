package com.steps.steps.Services;

import com.steps.steps.Repositories.LifeCycleRepository;
import org.springframework.stereotype.Service;

@Service
public class LifeCycleService {
    private final LifeCycleRepository lifeCycleRepository;

    public LifeCycleService(LifeCycleRepository lifeCycleRepository) {
        this.lifeCycleRepository = lifeCycleRepository;
    }

    public Long getCurrentWeek() {
        return lifeCycleRepository.getCurrentWeek();
    }

    public Long updateWeekNumber() {
        Long weekNumber = lifeCycleRepository.getCurrentWeek() + 1;
        return lifeCycleRepository.updateWeekNumber(weekNumber);
    }

    public Long getMaxLeaderboardId(){
        return lifeCycleRepository.getMaxLeaderboardId();
    }
    public Long updateLeaderboardId(){
        Long leaderboardId = lifeCycleRepository.getMaxLeaderboardId() + 1;
        return lifeCycleRepository.updateLeaderboardId(leaderboardId);
    }
    public Long getMaxTeamId(){
        return lifeCycleRepository.getMaxTeamId();
    }
    public Long updateMaxTeamId(){
        Long teamId = lifeCycleRepository.getMaxTeamId() + 1;
        return lifeCycleRepository.updateTeamId(teamId);
    }
}
