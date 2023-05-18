package com.steps.steps.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long weekCreated;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "teamId")
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "leaderboard_id")
    private Long leaderboardId;

    public Team(Long id, Long weekCreated) {
        this.id = id;
        this.name = id.toString();
        this.weekCreated = weekCreated;
        this.createdAt = java.sql.Date.valueOf(LocalDate.now());
    }

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboards(Long leaderboardId) {
        this.leaderboardId = leaderboardId;
    }
}