package com.takima.backskeleton;

import com.takima.backskeleton.models.Bataille_navale;
import com.takima.backskeleton.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackSkeletonApplication {

	@Autowired
	private GameService gameService;

	public static void main(String[] args) {
		SpringApplication.run(BackSkeletonApplication.class, args).getBean(Bataille_navale.class).startGame();;
	}

	public void startGame() {
		gameService.startGame();
	}

}
