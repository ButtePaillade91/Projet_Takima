
package com.takima.backskeleton.controller;

import com.takima.backskeleton.models.Projectile;
import com.takima.backskeleton.services.projectileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/projectiles")
public class projectileController {

    @Autowired
    private projectileService projectileService;

    @GetMapping
    public List<Projectile> getAllProjectiles() {
        return projectileService.getAllProjectile();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projectile> getProjectileById(@PathVariable int id) {
        Projectile projectile = projectileService.getProjectileById(id);
        return projectile != null ? ResponseEntity.ok(projectile) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Projectile createProjectile(@RequestBody Projectile projectile) {
        return projectileService.createProjectile(projectile);
    }

    @DeleteMapping("/{id}")
    public void deleteProjectile(@PathVariable int id) {
        projectileService.deleteProjectile(id);
    }
}

