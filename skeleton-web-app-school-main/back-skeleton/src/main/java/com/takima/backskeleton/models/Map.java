package io.takima.demo;

public class Map {
    int id;
    String nom;
    Cellule[] grille;
    public Map(int id, String nom, Cellule[] grille) {
        this.id = id;
        this.nom = nom;
        this.grille = grille;
    }
}
