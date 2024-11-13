package com.takima.backskeleton.controller;

import com.takima.backskeleton.models.Carte;
import com.takima.backskeleton.models.Joueur;
import com.takima.backskeleton.services.GameService; // Importation du service GameService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService game; // Déclaration du service GameService

    @Autowired
    public GameController(GameService gameService) { // Injection de dépendance du service GameService
        this.game = gameService;
    }

    // Démarrer une nouvelle partie
    @PostMapping("/start")
    public String startGame(@RequestBody Joueur joueur) {
        // Créer l'ordinateur et les cartes pour le jeu
        Joueur ordinateur = new Joueur();
        Carte carteJoueur = new Carte();
        Carte carteOrdinateur = new Carte();

        // Initialiser le jeu
        game.setJoueur(joueur); // Utilisation du service 'game'
        game.setOrdinateur(ordinateur);
        game.setCarteJoueur(carteJoueur);
        game.setCarteOrdinateur(carteOrdinateur);

        // Placer les bateaux aléatoirement
        game.shipPlacement(carteOrdinateur, ordinateur, carteJoueur, joueur);

        return "Jeu démarré avec succès !";
    }


}