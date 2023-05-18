package com.steps.steps.Controllers;

import com.steps.steps.Services.LifeCycleService;
import com.steps.steps.Services.RankingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lifeCycle")
public class LifeCycleController {
    private final LifeCycleService lifeCycleService;
    private final RankingsService rankingsService;

    @Autowired
    public LifeCycleController(RankingsService rankingsService, LifeCycleService lifeCycleService) {
        this.rankingsService = rankingsService;
        this.lifeCycleService = lifeCycleService;
    }

    @GetMapping
    public ResponseEntity<Long> getWeek() {
        Long weekId = lifeCycleService.getCurrentWeek();
        if (weekId != null) {
            return ResponseEntity.ok(weekId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/startNewWeek")
    public ResponseEntity<Long> updateWeek() {
        Long newWeekId = lifeCycleService.updateWeekNumber();
        if(newWeekId.equals(1))
        {
            rankingsService.createRankings();

        } else {
            rankingsService.updateRankings();
        }
        return ResponseEntity.status(HttpStatus.OK).body(newWeekId);
    }
}
