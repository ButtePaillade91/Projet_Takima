package com.takima.backskeleton.models;

public class Bateau {
    int id;
    int taille;
    int orientation;
    int vie;
    String nom;
    public Bateau(int id, int taille, int vie, String nom) {
        this.id = id;
        this.taille = taille;
        this.vie = vie;
        this.nom = nom;
    }
}
