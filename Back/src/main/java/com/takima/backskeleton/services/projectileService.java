package com.takima.backskeleton.services;


import com.takima.backskeleton.models.Projectile;
import com.takima.backskeleton.repository.projectileRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class projectileService {
    @Autowired
    private projectileRepository projectileRepository;

    public Projectile getProjectileById(int id) {
        return projectileRepository.findById(id).orElse(null);
    }

    @Transactional
    public Projectile createProjectile(Projectile projectile) {
        return projectileRepository.save(projectile);
    }

    public List<Projectile> getAllProjectile() {
        return projectileRepository.findAll();
    }

    @Transactional
    public void deleteProjectile(int id) {
        projectileRepository.deleteById(id);
    }
}
