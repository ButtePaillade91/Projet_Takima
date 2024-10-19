package com.takima.backskeleton.models;

import jakarta.persistence.*;

@Entity
@Table(name = "carte")
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String nom_carte;

    Cellule[] grille;

    public Carte(int id, String nom_carte, Cellule[] grille) {
        this.id = id;
        this.nom_carte = nom_carte;
        this.grille = grille;
    }

    public Carte() {}

    public void Toucher(Cellule[] grille, int i) {
        grille[i].bateauOccupe=null;
    }
}
