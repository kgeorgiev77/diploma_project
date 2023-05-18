package com.steps.steps.Services;
import com.steps.steps.Entities.Leaderboard;
import com.steps.steps.Entities.Team;
import com.steps.steps.Entities.User;
import com.steps.steps.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final LifeCycleService lifeCycleService;
    private final LeaderboardService leaderboardService;

    @Autowired
    public TeamService(LeaderboardService leaderboardService, LifeCycleService lifeCycleService,TeamRepository teamRepository) {
        this.leaderboardService = leaderboardService;
        this.lifeCycleService = lifeCycleService;
        this.teamRepository = teamRepository;
    }

    public Team getAvailableTeamOrNew(){
        Long weekNumber = lifeCycleService.getCurrentWeek();
        List<Team> listTeams = teamRepository.findByWeekNumber(weekNumber);
        
        Optional<Team> availableTeam = listTeams.stream()
                .filter(team -> team.getUsers().size() < 10)
                .findFirst();

        if (availableTeam.isPresent()) {
            return availableTeam.get();
        } else {
            Long newId = lifeCycleService.updateMaxTeamId();
            Team newTeam = new Team(newId,weekNumber);
            if (weekNumber > 1){
                Long existingLeaderboardId = lifeCycleService.getMaxLeaderboardId();
                Leaderboard leaderboard = leaderboardService.getLeaderboardById(existingLeaderboardId);
                List<Team> leaderboardTeams = leaderboard.getTeams();
                if (leaderboardTeams.size() < 10){
                    newTeam.setLeaderboards(existingLeaderboardId);

                    List<Team> newTeams = new ArrayList<>(leaderboardTeams);

                    newTeams.add(newTeam);
                    leaderboard.setTeams(newTeams);
                    leaderboardService.updateLeaderboard(existingLeaderboardId, leaderboard);
                }
                else
                {
                    Long newLeaderboardId = lifeCycleService.updateLeaderboardId();
                    newTeam.setLeaderboards(newLeaderboardId);
                    Leaderboard newLeaderboard = new Leaderboard(newLeaderboardId,Collections.singletonList(newTeam));
                    leaderboardService.createLeaderboard(newLeaderboard);
                }
            }
            return createTeam(newTeam);
        }
    }

    public List<Team> getTeamsByLeaderboardIdOrderedBySteps(Long id) {
        List<Team> teams = teamRepository.findByLeaderboardId(id);

        teams.sort(Comparator.comparingDouble(TeamService::calculateTeamSteps));

        return teams;
    }

    public List<Team> getTeamsOrderedBySteps() {
        List<Team> teams = teamRepository.getAllTeams();

        teams.sort(Comparator.comparingDouble(TeamService::calculateTeamSteps));

        return teams;
    }

    private static double calculateTeamSteps(Team team) {
        double stepsSum = 0;
        int usersCount = team.getUsers().size();

        for (User user : team.getUsers()) {
            stepsSum += user.getSteps().size();
        }

        return stepsSum / usersCount;
    }

    public Team getTeamById(Long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        return teamOptional.orElse(null);
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Team updateTeam(Long id, Team team) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        if (teamOptional.isPresent()) {
            Team existingTeam = teamOptional.get();
            existingTeam.setName(team.getName());
            return teamRepository.save(existingTeam);
        } else {
            return null;
        }
    }

    public void updateTeamRanking(Long maxLeaderboardId) {
        for (long i = 1L; i <= maxLeaderboardId; i++) {
            List<Team> teams = this.getTeamsByLeaderboardIdOrderedBySteps(i);
            List<Team> promotedList = teams.subList(0, 5);
            List<Team> relegatedList = teams.subList(5, 10);

            if (i > 1L){
                for (Team promotedTeam : promotedList){
                    Long newId = i + 1;
                    promotedTeam.setLeaderboards(newId);
                    this.updateTeam(promotedTeam.getId(),promotedTeam);
                }}

            if (i < maxLeaderboardId){
                for (Team relegatedTeam : relegatedList){
                    Long newId = i - 1;
                    relegatedTeam.setLeaderboards(newId);
                    this.updateTeam(relegatedTeam.getId(),relegatedTeam);
                }
            }
        }
    }

    public boolean deleteTeam(Long id) {
        Optional<Team> teamOptional = teamRepository.findById(id);
        if (teamOptional.isPresent()) {
            teamRepository.delete(teamOptional.get());
            return true;
        } else {
            return false;
        }
    }
}
