package com.takima.backskeleton.controller;

import com.takima.backskeleton.models.Carte;
import com.takima.backskeleton.services.carteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cartes")
public class carteController {

    @Autowired
    private carteService carteService;

    @GetMapping
    public List<Carte> getAllCartes() {
        return carteService.getAllCartes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carte> getCarteById(@PathVariable int id) {
        Carte carte = carteService.getCarteById(id);
        return carte != null ? ResponseEntity.ok(carte) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Carte createCarte(@RequestBody Carte carte) {
        return carteService.createCarte(carte);
    }

    @DeleteMapping("/{id}")
    public void deleteCarte(@PathVariable int id) {
        carteService.deleteCarte(id);
    }
}
