package com.steps.steps.Controllers;

import com.steps.steps.Entities.Leaderboard;
import com.steps.steps.Services.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaderboards")
public class LeaderboardController {
    private final LeaderboardService leaderboardService;

    @Autowired
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leaderboard> getLeaderboardById(@PathVariable Long id) {
        Leaderboard leaderboard = leaderboardService.getLeaderboardById(id);
        if (leaderboard != null) {
            return ResponseEntity.ok(leaderboard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*@PostMapping
    public ResponseEntity<Leaderboard> createLeaderboard(@RequestBody Leaderboard leaderboard) {
        Leaderboard createdLeaderboard = leaderboardService.createLeaderboard(leaderboard);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLeaderboard);
    }*/

    @PutMapping("/{id}/edit")
    public ResponseEntity<Leaderboard> updateLeaderboard(@PathVariable Long id, @RequestBody Leaderboard leaderboard) {
        Leaderboard updatedLeaderboard = leaderboardService.updateLeaderboard(id, leaderboard);
        if (updatedLeaderboard != null) {
            return ResponseEntity.ok(updatedLeaderboard);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteLeaderboard(@PathVariable Long id) {
        boolean deleted = leaderboardService.deleteLeaderboard(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}