package com.takima.backskeleton.models;
public class Carte {
    int id;
    String nom;
    Cellule[] grille;
    public Carte(int id, String nom, Cellule[] grille) {
        this.id = id;
        this.nom = nom;
        this.grille = grille;
    }
}
