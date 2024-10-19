package com.takima.backskeleton.services.impl;

import com.takima.backskeleton.models.Joueur;
import com.takima.backskeleton.models.Carte;
import com.takima.backskeleton.models.Projectile;
import com.takima.backskeleton.repository.joueurRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class joueurService {
    @Autowired
    private joueurRepository joueurRepository;

    public Joueur getJoueurById(int id) {
        return joueurRepository.findById(id).orElse(null);
    }

    @Transactional
    public Joueur createJoueur(Joueur joueur) {
        return joueurRepository.save(joueur);
    }

    public List<Joueur> getAllJoueurs() {
        return joueurRepository.findAll();
    }

    @Transactional
    public void deleteJoueur(int id) {
        joueurRepository.deleteById(id);
    }

    // Logique de tir (si nécessaire)
    @Transactional
    public int tirer(int idJoueur, int positionX, int positionY, Carte carte, List<Projectile> munitions, int munitionUtilisee) {
        Joueur joueur = getJoueurById(idJoueur);

        if (joueur != null) {
            // Appelle la méthode "tirer" dans l'entité Joueur
            return joueur.tirer(positionX, positionY, carte, munitions, munitionUtilisee);
        }

        return -1; // Joueur introuvable
    }
}
