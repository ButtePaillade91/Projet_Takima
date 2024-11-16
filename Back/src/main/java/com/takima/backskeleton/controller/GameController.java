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
    public ResponseEntity<Map<String, Object>> computerTurn(
            @RequestBody Carte carte
    ) {
        boolean result = game.computerTurn( carte);
        return ResponseEntity.ok(Map.of(
                "message", result ? "L'ordinateur a gagné !" : "Tour terminé.",
                "victory", result
        ));
    }

    // Check victory status
    @GetMapping("/game/check-victory")
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
