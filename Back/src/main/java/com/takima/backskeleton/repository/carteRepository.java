package com.takima.backskeleton.repository;

import com.takima.backskeleton.models.Carte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  carteRepository extends JpaRepository<Carte, Integer>{
}
