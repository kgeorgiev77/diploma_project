package com.steps.steps.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "leaderboards")
public class Leaderboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "leaderboard_id")
    private List<Team> teams;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "modified_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;

    public Leaderboard(Long id, List<Team> teams) {
        this.id = id;
        this.teams = teams;
        this.dateCreated = java.sql.Date.valueOf(LocalDate.now());;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Team> getTeams() {
        return teams;
    }
    public void setTeams(List<Team> teams) { this.teams = teams; }

    public Date getCreatedAt() {
        return dateCreated;
    }

    public void setCreatedAt(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getModifiedAt() {
        return dateModified;
    }

    public void setModifiedAt(Date dateModified) {
        this.dateModified = dateModified;
    }
}