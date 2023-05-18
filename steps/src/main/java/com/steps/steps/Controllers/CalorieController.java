package com.steps.steps.Controllers;

import com.steps.steps.Entities.Calorie;
import com.steps.steps.Services.CalorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calories")
public class CalorieController {
    private final CalorieService calorieService;

    @Autowired
    public CalorieController(CalorieService calorieService) {
        this.calorieService = calorieService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Calorie>> getCaloriesByUserId(@PathVariable Long userId) {
        List<Calorie> calories = calorieService.getCaloriesByUserId(userId);
        return ResponseEntity.ok(calories);
    }

    @PostMapping
    public ResponseEntity<Calorie> createCalorie(@RequestBody Calorie calorie) {
        Calorie createdCalorie = calorieService.createCalorie(calorie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCalorie);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<Calorie> updateCalorie(@PathVariable Long id, @RequestBody Calorie calorie) {
        Calorie updatedCalorie = calorieService.updateCalorie(id, calorie);
        if (updatedCalorie != null) {
            return ResponseEntity.ok(updatedCalorie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteCalorie(@PathVariable Long id) {
        boolean deleted = calorieService.deleteCalorie(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}