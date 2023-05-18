package com.steps.steps.Services;

import com.steps.steps.Entities.UserTeam;
import com.steps.steps.Helpers.UserTeamId;
import com.steps.steps.Repositories.UserTeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTeamService {
    private final UserTeamRepository userTeamRepository;

    @Autowired
    public UserTeamService(UserTeamRepository userTeamRepository) {
        this.userTeamRepository = userTeamRepository;
    }

    public UserTeam createUserTeam(UserTeam userTeam) {
        return userTeamRepository.save(userTeam);
    }

    public boolean deleteUserTeam(Long userId, Long teamId) {
        Optional<UserTeam> userTeamOptional = userTeamRepository.findById(new UserTeamId(userId, teamId));
        if (userTeamOptional.isPresent()) {
            userTeamRepository.delete(userTeamOptional.get());
            return true;
        } else {
            return false;
        }
    }
}