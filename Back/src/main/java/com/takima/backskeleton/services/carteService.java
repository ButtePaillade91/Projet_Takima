package com.takima.backskeleton.services;


import com.takima.backskeleton.models.Carte;
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
        return carteRepository.findById(id).orElse(null);
    }

    @Transactional
    public Carte createCarte(Carte carte) {
        return carteRepository.save(carte);
    }

    public List<Carte> getAllCartes() {
        return carteRepository.findAll();
    }

    @Transactional
    public void deleteCarte(int id) {
        carteRepository.deleteById(id);
    }
}
