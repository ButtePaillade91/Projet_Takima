
package com.takima.backskeleton.controller;

import com.takima.backskeleton.models.Joueur;
import com.takima.backskeleton.services.joueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/joueurs")
public class joueurController {

    @Autowired
    private joueurService joueurService;

    @GetMapping
    public List<Joueur> getAllJoueurs() {
        return joueurService.getAllJoueurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Joueur> getJoueurById(@PathVariable int id) {
        Joueur joueur = joueurService.getJoueurById(id);
        return joueur != null ? ResponseEntity.ok(joueur) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Joueur createJoueur(@RequestBody Joueur joueur) {
        return joueurService.createJoueur(joueur);
    }

    @DeleteMapping("/{id}")
    public void deleteJoueur(@PathVariable int id) {
        joueurService.deleteJoueur(id);
    }
}

