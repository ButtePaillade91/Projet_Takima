package io.takima.demo;

public class Joueur {
    int id;
    String pseudo;
    Projectile[] munitions;
    public Joueur(int id, String pseudo, Projectile[] munitions) {
        this.id = id;
        this.pseudo = pseudo;
        this.munitions = munitions;
    }
}