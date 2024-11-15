package com.takima.backskeleton.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "projectile")
public class Projectile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_projectile;
    private String type_projectile;


    public Projectile() {}

    public Projectile(int id_projectile, String type_projectile) {
        this.id_projectile = id_projectile;

        this.type_projectile = type_projectile;
    }
}
