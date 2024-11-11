package com.takima.backskeleton.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Entity
@Table(name = "carte")
@Service
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String nom_carte;

    @Transient
    public Cellule[][] grille;

    public Carte(int id, String nom_carte) {
        this.id = id;
        this.nom_carte = nom_carte;
        initializeGrille();
    }

    public Carte() {}

    private void initializeGrille() {
        this.grille = new Cellule[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grille[i][j] = new Cellule(0, i, j, null); // Initialisation sans bateau
            }
        }
    }

    public void Toucher(Cellule[][] grille, int i, int b) {
        grille[i][b].bateauOccupe=null;
    }


}
