package com.steps.steps.Entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;


@Entity
@Table(name = "calories")
public class Calorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "calories_burned", nullable = false)
    private BigDecimal caloriesBurned;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    public Calorie(Long id, User user, BigDecimal caloriesBurned, Date date) {
        this.id = id;
        this.user = user;
        this.caloriesBurned = caloriesBurned;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(BigDecimal caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
