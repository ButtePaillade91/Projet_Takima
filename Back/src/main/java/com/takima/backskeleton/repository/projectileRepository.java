package com.takima.backskeleton.repository;


import com.takima.backskeleton.models.Projectile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface projectileRepository extends JpaRepository<Projectile, Integer> {
}
