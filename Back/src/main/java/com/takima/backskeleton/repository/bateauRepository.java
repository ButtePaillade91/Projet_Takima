package com.takima.backskeleton.repository;

import com.takima.backskeleton.models.Bateau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bateauRepository extends JpaRepository<Bateau, Integer> {
}
