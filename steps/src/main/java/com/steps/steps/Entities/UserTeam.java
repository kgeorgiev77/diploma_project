package com.steps.steps.Entities;


import com.steps.steps.Helpers.UserTeamId;
import jakarta.persistence.*;

@Entity
@Table(name = "user_teams")
public class UserTeam {

    @EmbeddedId
    private UserTeamId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private Team team;

    public UserTeam(User user, Team team) {
        this.user = user;
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}