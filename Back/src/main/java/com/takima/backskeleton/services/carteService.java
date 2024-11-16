package com.takima.backskeleton.services;


import com.takima.backskeleton.models.Carte;
import com.takima.backskeleton.models.Cellule;
import com.takima.backskeleton.repository.carteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class carteService {
    @Autowired
    private carteRepository carteRepository;

    public Carte getCarteById(int id) {
        Carte carte = carteRepository.findById(id).orElse(null);
        if (carte != null && carte.getGrille() == null) {
            // Initialize the grille if it's null
            initializeGrilleForCarte(carte);
        }
        return carte;
    }

    private void initializeGrilleForCarte(Carte carte) {
        Cellule[][] grille = new Cellule[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grille[i][j] = new Cellule(0, null); // Default Cellule initialization
            }
        }
        carte.setGrille(grille);
    }

    @Transactional
    public Carte createCarte(Carte carte) {
        // Initialize the grille if it's null
        if (carte.getGrille() == null) {
            Cellule[][] grille = new Cellule[10][10];
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    grille[i][j] = new Cellule(0, null); // Default Cellule initialization
                }
            }
            carte.setGrille(grille);
        }
        return carteRepository.save(carte);
    }


    public List<Carte> getAllCartes() {
        List<Carte> cartes = carteRepository.findAll();
        for (Carte carte : cartes) {
            if (carte.getGrille() == null) {
                initializeGrilleForCarte(carte);
            }
        }
        return cartes;
    }


    @Transactional
    public void deleteCarte(int id) {
        carteRepository.deleteById(id);
    }
}
