package com.steps.steps.Repositories;

import com.steps.steps.Entities.Calorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalorieRepository extends JpaRepository<Calorie, Long> {
    List<Calorie> findByUserId(Long userId);
}