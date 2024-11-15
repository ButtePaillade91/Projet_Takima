
package com.takima.backskeleton.controller;

import com.takima.backskeleton.models.Projectile;
import com.takima.backskeleton.services.projectileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class projectileController {

    @Autowired
    private projectileService projectileService;

    @GetMapping("/projectile/all")
    public List<Projectile> getAllProjectiles() {
        return projectileService.getAllProjectile();
    }

    @GetMapping("/projectile/{id}")
    public ResponseEntity<Projectile> getProjectileById(@PathVariable int id) {
        Projectile projectile = projectileService.getProjectileById(id);
        return projectile != null ? ResponseEntity.ok(projectile) : ResponseEntity.notFound().build();
    }

    @PostMapping("/projectile/create")
    public Projectile createProjectile(@RequestBody Projectile projectile) {
        return projectileService.createProjectile(projectile);
    }

    @DeleteMapping("/projectile/delete/{id}")
    public void deleteProjectile(@PathVariable int id) {
        projectileService.deleteProjectile(id);
    }
}

