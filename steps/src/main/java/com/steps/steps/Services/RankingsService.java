package com.steps.steps.Services;

import com.steps.steps.Entities.Leaderboard;
import com.steps.steps.Entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingsService {
    private final TeamService teamService;
    private final LeaderboardService leaderboardService;

    @Autowired
    public RankingsService(TeamService teamService, LeaderboardService leaderboardService) {
        this.teamService = teamService;
        this.leaderboardService = leaderboardService;
    }

    public void createRankings()
    {
        List<Team> teams = teamService.getTeamsOrderedBySteps();

        if (!(teams.isEmpty())){
            int leaderBoardSize = 10;
            int index = 0;
            Long leaderboardId = 1L;

            while (index < teams.size()) {
                List<Team> leagueTeams = teams.subList(index, Math.min(index + leaderBoardSize, teams.size()));

                for (Team currentTeam : leagueTeams) {
                    currentTeam.setLeaderboards(leaderboardId);
                    teamService.updateTeam(currentTeam.getId(),currentTeam);
                }

                Leaderboard newLeaderboard = new Leaderboard(leaderboardId, leagueTeams);
                leaderboardService.createLeaderboard(newLeaderboard);

                leaderboardId += 1;
                index += leaderBoardSize;
            }
        }
    }

    public void updateRankings()
    {
        Long maxLeaderboardId = leaderboardService.getMaxLeaderboardId();

        teamService.updateTeamRanking(maxLeaderboardId);

        for (Long i = 1L; i < maxLeaderboardId; i++) {
            List<Team> teams = teamService.getTeamsByLeaderboardIdOrderedBySteps(i);

            Leaderboard leaderboardToUpdate = leaderboardService.getLeaderboardById(i);
            leaderboardToUpdate.setTeams(teams);

            leaderboardService.updateLeaderboard(i, leaderboardToUpdate);
        }
    }
}
