package com.takima.backskeleton.controller;

import com.takima.backskeleton.models.Carte;
import com.takima.backskeleton.models.Joueur;
import com.takima.backskeleton.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService game; // Service for game logic

    @Autowired
    public GameController(GameService gameService) {
        this.game = gameService;
    }

    // Start a new game
    @PostMapping("/start")
    public ResponseEntity<Map<String, String>> startGame(@RequestBody Joueur joueur) {
        // Create opponent and boards
        Joueur ordinateur = new Joueur();
        Carte carteJoueur = new Carte();
        Carte carteOrdinateur = new Carte();

        // Initialize the game
        game.setJoueur(joueur);
        game.setOrdinateur(ordinateur);
        game.setCarteJoueur(carteJoueur);
        game.setCarteOrdinateur(carteOrdinateur);

        // Place ships randomly
        game.shipPlacement(carteOrdinateur, ordinateur, carteJoueur, joueur);

        // Return a success response
        return ResponseEntity.ok(Map.of("message", "Jeu démarré avec succès !"));
    }

    // Player's turn
    @PostMapping("/player-turn")
    public ResponseEntity<Map<String, Object>> playerTurn(
            @RequestParam int x,
            @RequestParam int y,
            @RequestParam int munitionType) {
        boolean result = game.playerTurn(game.getJoueur(), game.getCarteOrdinateur());
        return ResponseEntity.ok(Map.of(
                "message", result ? "Vous avez gagné !" : "Tour terminé.",
                "victory", result
        ));
    }

    // Computer's turn
    @GetMapping("/computer-turn")
    public ResponseEntity<Map<String, Object>> computerTurn() {
        boolean result = game.computerTurn(game.getOrdinateur(), game.getCarteJoueur());
        return ResponseEntity.ok(Map.of(
                "message", result ? "L'ordinateur a gagné !" : "Tour terminé.",
                "victory", result
        ));
    }

    // Check victory status
    @GetMapping("/check-victory")
    public ResponseEntity<Map<String, Boolean>> checkVictory(@RequestParam String target) {
        boolean victory;
        if ("player".equalsIgnoreCase(target)) {
            victory = game.checkVictory(game.getCarteOrdinateur());
        } else if ("computer".equalsIgnoreCase(target)) {
            victory = game.checkVictory(game.getCarteJoueur());
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", true));
        }
        return ResponseEntity.ok(Map.of("victory", victory));
    }
}
