package com.takima.backskeleton.controller;

import com.takima.backskeleton.models.Carte;
import com.takima.backskeleton.models.Joueur;
import com.takima.backskeleton.models.PoserBateau;
import com.takima.backskeleton.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameService game; // Service for game logic

    @Autowired
    public GameController(GameService gameService) {
        this.game = gameService;
    }


    // New endpoint for ship placement
    @PostMapping("/game/ship-placement")
    public ResponseEntity<Map<String, String>> shipPlacement(
            @RequestBody PoserBateau poserBateau) {
        game.shipPlacement(poserBateau.getCarte(), poserBateau.getBateaux());
        return ResponseEntity.ok(Map.of("message", "Placement des bateaux terminé !"));
    }


    // Computer's turn
    @PostMapping("/game/computer-turn")
    public ResponseEntity<String> computerTurn(@RequestBody Carte carte) {
        try {
            System.out.println("Réception de la carte pour le tour de l'ordinateur : " + carte);
            game.computerTurn(carte);
            return ResponseEntity.ok("Tour terminé");
        } catch (Exception e) {
            System.err.println("Erreur dans l'endpoint /game/computer-turn : " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du tour de l'ordinateur");
        }
    }


    // Check victory status
    @PostMapping("/game/check-victory")
    public ResponseEntity<Boolean> checkVictory(@RequestBody Carte carte) {
        boolean isVictory = game.checkVictory(carte);
        return ResponseEntity.ok(isVictory);
    }


    @PostMapping("/game/tirer")
    public ResponseEntity<Integer> tirer(
            @RequestBody Carte carte, // Wrap both joueur and carte
            @RequestParam int positionX,
            @RequestParam int positionY
    ) {
        int result = game.tirer( positionX, positionY, carte);
        return ResponseEntity.ok(result);
    }

}
