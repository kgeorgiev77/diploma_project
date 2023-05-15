package com.steps.steps.Repositories;
import com.steps.steps.Entities.UserTeam;
import com.steps.steps.Helpers.UserTeamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTeamRepository extends JpaRepository<UserTeam, Long> {
    Optional<UserTeam> findById(UserTeamId userTeamId);
}
