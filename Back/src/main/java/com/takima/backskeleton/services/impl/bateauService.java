package com.takima.backskeleton.services.impl;


import com.takima.backskeleton.models.Bateau;
import com.takima.backskeleton.models.Joueur;
import com.takima.backskeleton.repository.bateauRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bateauService {
    @Autowired
    private bateauRepository BateauRepository;

    public Bateau getBateauById(int id) {
        return BateauRepository.findById(id).orElse(null);
    }

    @Transactional
    public Bateau createBateau(Bateau bateau) {
        return BateauRepository.save(bateau);
    }

    public List<Bateau> getAllBateaux() {
        return BateauRepository.findAll();
    }

    @Transactional
    public void deleteBateau(int id) {
        BateauRepository.deleteById(id);
    }
}
