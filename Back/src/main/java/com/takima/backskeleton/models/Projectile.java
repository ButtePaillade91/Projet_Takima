package com.takima.backskeleton.models;

import jakarta.persistence.*;

@Entity
@Table(name = "projectile")
public class Projectile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int quantite;
    String type_projectile;

    @ManyToOne
    @JoinColumn(name = "id_joueur", nullable = false)
    private Joueur joueur;

    public Projectile() {}

    public Projectile(int id, int quantite, String type_projectile) {
        this.id = id;
        this.quantite = quantite;
        this.type_projectile = type_projectile;
    }
}
