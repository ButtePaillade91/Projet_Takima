package com.takima.backskeleton;

import com.takima.backskeleton.models.*;
import com.takima.backskeleton.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BackSkeletonApplication {



	public static void main(String[] args) {

		Projectile cannonball = new Projectile(1, 10, "Cannonball"); // Assuming a quantity of 10 for example
		Projectile torpedo = new Projectile(2, 5, "Torpedo"); // Assuming a quantity of 5 for example

		Bateau spanish = new Bateau(1, 2, 2, "Spanish");
		Bateau german = new Bateau(2, 5, 5, "German");
		Bateau titanic = new Bateau(3, 3, 3, "Titanic");
		Bateau victoria = new Bateau(4, 4, 4, "Victoria");
		Bateau cruiser = new Bateau(5, 6, 6, "Cruiser");

		Carte carteJoueur = new Carte(1, "Mer baltique");
		Carte carteOrdinateur = new Carte(2, "Mer baltiques");

		List<Bateau> bateauxMatuidi = Arrays.asList(spanish, german);
		List<Projectile> munitionsMatuidi = Arrays.asList(cannonball);

		Joueur matuidi = new Joueur(1, "Matuidi", munitionsMatuidi, bateauxMatuidi);

		List<Bateau> bateauxRAKO = Arrays.asList(titanic);
		List<Projectile> munitionsRAKO = Arrays.asList(torpedo);

		Joueur rako = new Joueur(2, "RAKO", munitionsRAKO, bateauxRAKO);

		SpringApplication.run(BackSkeletonApplication.class, args).getBean(GameService.class).startGame(rako, matuidi, carteJoueur, carteOrdinateur);
	}



}
