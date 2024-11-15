package com.takima.backskeleton.models;

import jakarta.persistence.*;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@Entity
@Table(name = "joueur")
@Service
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_joueur;
    private String pseudo;


    public Joueur() {}
    public Joueur(int id_joueur, String pseudo) {
        this.id_joueur = id_joueur;
        this.pseudo = pseudo;
    }



    public int tirer(int position_x, int position_y, Carte map) {
        int resultat = 0; //dans l'eau

        // Vérifie les limites de la grille pour éviter une sortie de tableau
        if (position_x < 0 || position_x >= 10 || position_y < 0 || position_y >= 10) {
            System.out.println("Tir en dehors de la carte !");
            return resultat;
        }

        Cellule cellule = map.getGrille()[position_x][position_y];

        // Munition basique
        if (cellule.getBateauOccupe() != null) {
            cellule.getBateauOccupe().setVie(cellule.getBateauOccupe().getVie() - 1);

            if (cellule.getBateauOccupe().getVie() <= 0) {
                    map.Toucher(map.getGrille(), position_x, position_y);
                    resultat = 2; // Coulé
            } else {
                    map.Toucher(map.getGrille(), position_x, position_y);
                    resultat = 1; // Touché
            }
        }


        return resultat;
    }






}
