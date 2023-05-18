package com.steps.steps.Controllers;

import com.steps.steps.Entities.UserTeam;
import com.steps.steps.Services.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user_teams")
public class UserTeamController {
    private final UserTeamService userTeamService;

    @Autowired
    public UserTeamController(UserTeamService userTeamService) {
        this.userTeamService = userTeamService;
    }

    @PostMapping
    public ResponseEntity<UserTeam> createUserTeam(@RequestBody UserTeam userTeam) {
        UserTeam createdUserTeam = userTeamService.createUserTeam(userTeam);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserTeam);
    }

    @DeleteMapping("/{userId}/{teamId}")
    public ResponseEntity<Void> deleteUserTeam(@PathVariable Long userId, @PathVariable Long teamId) {
        boolean deleted = userTeamService.deleteUserTeam(userId, teamId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}