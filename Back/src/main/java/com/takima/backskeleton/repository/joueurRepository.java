package com.takima.backskeleton.repository;


import com.takima.backskeleton.models.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface joueurRepository extends JpaRepository<Joueur, Integer> {
    // Possibilité d'ajouter des méthodes spécifiques si nécessaire
}