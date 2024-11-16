package com.takima.backskeleton.controller;

import com.takima.backskeleton.models.Carte;
import com.takima.backskeleton.models.Joueur;
import com.takima.backskeleton.models.PoserBateau;
import com.takima.backskeleton.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("/game/computer-turn")
    public ResponseEntity<String> computerTurn(
            @RequestBody Carte carte
    ) {
        game.computerTurn( carte);
        return ResponseEntity.ok("tour terminé");
    }

    // Check victory status
    @GetMapping("/game/check-victory")
    public ResponseEntity<String> checkVictory(@RequestBody Carte carte) {
        game.checkVictory(carte);
        return ResponseEntity.ok("victory");
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
