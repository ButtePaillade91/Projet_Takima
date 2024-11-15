
package com.takima.backskeleton.controller;

import com.takima.backskeleton.models.Carte;
import com.takima.backskeleton.models.Joueur;
import com.takima.backskeleton.models.Projectile;
import com.takima.backskeleton.services.joueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class joueurController {

    @Autowired
    private joueurService joueurService;

    @GetMapping("joueur/all")
    public List<Joueur> getAllJoueurs() {
        return joueurService.getAllJoueurs();
    }

    @GetMapping("/joueur/{id}")
    public ResponseEntity<Joueur> getJoueurById(@PathVariable int id) {
        Joueur joueur = joueurService.getJoueurById(id);
        return joueur != null ? ResponseEntity.ok(joueur) : ResponseEntity.notFound().build();
    }

    @PostMapping("/joueur/create")
    public Joueur createJoueur(@RequestBody Joueur joueur) {
        return joueurService.createJoueur(joueur);
    }

    @DeleteMapping("/joueur/delete/{id}")
    public void deleteJoueur(@PathVariable int id) {
        joueurService.deleteJoueur(id);
    }

    @PostMapping("/joueur/{id}/tirer")
    public ResponseEntity<Integer> tirer(
            @PathVariable int id,
            @RequestParam int positionX,
            @RequestParam int positionY,
            @RequestBody Carte carte) {

        int result = joueurService.tirer(id, positionX, positionY, carte);
        return ResponseEntity.ok(result);
    }

}

