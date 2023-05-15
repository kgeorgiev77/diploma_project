package com.steps.steps.Controllers;

import com.steps.steps.Entities.Step;
import com.steps.steps.Services.StepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/steps")
public class StepController {
    private final StepService stepService;

    @Autowired
    public StepController(StepService stepService) {
        this.stepService = stepService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Step>> getStepsByUserId(@PathVariable Long userId) {
        List<Step> steps = stepService.getStepsByUserId(userId);
        return ResponseEntity.ok(steps);
    }

    @PostMapping
    public ResponseEntity<Step> createStep(@RequestBody Step step) {
        Step createdStep = stepService.createStep(step);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStep);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Step> updateStep(@PathVariable Long id, @RequestBody Step step) {
        Step updatedStep = stepService.updateStep(id, step);
        if (updatedStep != null) {
            return ResponseEntity.ok(updatedStep);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStep(@PathVariable Long id) {
        boolean deleted = stepService.deleteStep(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
