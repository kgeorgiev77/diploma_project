package com.steps.steps.Services;

import com.steps.steps.Entities.Step;
import com.steps.steps.Repositories.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StepService {
    private final StepRepository stepRepository;

    @Autowired
    public StepService(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    public List<Step> getStepsByUserId(Long userId) {
        return stepRepository.findByUserId(userId);
    }

    public Step createStep(Step step) {
        return stepRepository.save(step);
    }

    public Step updateStep(Long id, Step step) {
        Optional<Step> stepOptional = stepRepository.findById(id);
        if (stepOptional.isPresent()) {
            Step existingStep = stepOptional.get();
            existingStep.setStepCount(step.getStepCount());
            existingStep.setDate(step.getDate());
            return stepRepository.save(existingStep);
        } else {
            return null;
        }
    }

    public boolean deleteStep(Long id) {
        Optional<Step> stepOptional = stepRepository.findById(id);
        if (stepOptional.isPresent()) {
            stepRepository.delete(stepOptional.get());
            return true;
        } else {
            return false;
        }
    }
}
