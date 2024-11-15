package com.takima.backskeleton.controller;

import com.takima.backskeleton.models.Bateau;
import com.takima.backskeleton.services.bateauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class bateauController {

    @Autowired
    private bateauService bateauService;

    @GetMapping("/bateaux/all")
    public List<Bateau> getAllBateaux() {
        return bateauService.getAllBateaux();
    }

    @GetMapping("/bateaux/{id}")
    public ResponseEntity<Bateau> getBateauById(@PathVariable int id) {
        Bateau bateau = bateauService.getBateauById(id);
        return bateau != null ? ResponseEntity.ok(bateau) : ResponseEntity.notFound().build();
    }

    @PostMapping("/bateaux/create")
    public Bateau createBateau(@RequestBody Bateau bateau) {
        return bateauService.createBateau(bateau);
    }


    @DeleteMapping("/bateaux/delete/{id}")
    public void deleteBateau(@PathVariable int id) {
       bateauService.deleteBateau(id);
    }
}
