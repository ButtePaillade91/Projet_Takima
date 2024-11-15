package com.takima.backskeleton.controller;

import com.takima.backskeleton.models.Carte;
import com.takima.backskeleton.services.carteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class carteController {

    @Autowired
    private carteService carteService;

    @GetMapping("/carte/all")
    public List<Carte> getAllCartes() {
        return carteService.getAllCartes();
    }

    @GetMapping("/carte/{id}")
    public ResponseEntity<Carte> getCarteById(@PathVariable int id) {
        Carte carte = carteService.getCarteById(id);
        return carte != null ? ResponseEntity.ok(carte) : ResponseEntity.notFound().build();
    }

    @PostMapping("/carte/create")
    public Carte createCarte(@RequestBody Carte carte) {
        return carteService.createCarte(carte);
    }

    @DeleteMapping("/carte/delete/{id}")
    public void deleteCarte(@PathVariable int id) {
        carteService.deleteCarte(id);
    }
}
