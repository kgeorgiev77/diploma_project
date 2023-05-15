package com.steps.steps.Services;

import com.steps.steps.Entities.Calorie;
import com.steps.steps.Repositories.CalorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalorieService {
    private final CalorieRepository calorieRepository;

    @Autowired
    public CalorieService(CalorieRepository calorieRepository) {
        this.calorieRepository = calorieRepository;
    }

    public List<Calorie> getCaloriesByUserId(Long userId) {
        return calorieRepository.findByUserId(userId);
    }

    public Calorie createCalorie(Calorie calorie) {
        return calorieRepository.save(calorie);
    }

    public Calorie updateCalorie(Long id, Calorie calorie) {
        Optional<Calorie> calorieOptional = calorieRepository.findById(id);
        if (calorieOptional.isPresent()) {
            Calorie existingCalorie = calorieOptional.get();
            existingCalorie.setCaloriesBurned(calorie.getCaloriesBurned());
            existingCalorie.setDate(calorie.getDate());
            return calorieRepository.save(existingCalorie);
        } else {
            return null;
        }
    }

    public boolean deleteCalorie(Long id) {
        Optional<Calorie> calorieOptional = calorieRepository.findById(id);
        if (calorieOptional.isPresent()) {
            calorieRepository.delete(calorieOptional.get());
            return true;
        } else {
            return false;
        }
    }
}
