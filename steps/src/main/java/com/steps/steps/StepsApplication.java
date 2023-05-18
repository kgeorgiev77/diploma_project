package com.steps.steps;

import com.steps.steps.Controllers.StepController;
import com.steps.steps.Controllers.TeamController;
import com.steps.steps.Controllers.UserController;
import com.steps.steps.Entities.Step;
import com.steps.steps.Entities.Team;
import com.steps.steps.Entities.User;
import com.steps.steps.Helpers.NewUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class StepsApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(StepsApplication.class, args);
		UserController userController = context.getBean(UserController.class);
		TeamController teamController = context.getBean(TeamController.class);
		StepController stepController = context.getBean(StepController.class);

		System.out.println();
		System.out.println("---------------- Test Controller Requests to Database ----------------");

		// Test user retrieval by ID
		long userId = 1;
		ResponseEntity<User> retrievedUser = userController.getUserById(userId);
		System.out.println("Retrieved user: " + retrievedUser.toString());

		// Test team retrieval by ID
		long teamId = 1;
		ResponseEntity<Team> retrievedTeam = teamController.getTeamById(teamId);
		System.out.println("Retrieved team: " + retrievedTeam);

		System.out.println("-----------------------------");

		// Retrieve all steps for the user
		ResponseEntity<List<Step>> response = stepController.getStepsByUserId(userId);
		if (response.getStatusCode() == HttpStatus.OK) {
			List<Step> steps = response.getBody();
			for (Step step : steps) {
				System.out.println("Step ID: " + step.getId());
				System.out.println("Step Count: " + step.getStepCount());
				System.out.println("Date: " + step.getDate());
				System.out.println("-----------------------------");
			}
		} else {
			System.out.println("Failed to retrieve steps for user with ID " + userId);
		}

		// Test user creation
		NewUser newUser = new NewUser("plamen","password123","plamen@mail.com");
		ResponseEntity<User> createdUser = userController.createUser(newUser);
		System.out.println("Created user: " + createdUser);
	}
}
