package com.takima.backskeleton.models;

import jakarta.persistence.*;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "joueur")
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String pseudo;

    @OneToMany
    @JoinTable(
            name = "contient",
            joinColumns = @JoinColumn(name = "id_joueur"),
            inverseJoinColumns = @JoinColumn(name = "id_projectile")
    )
    private List<Projectile> munitions;

    @OneToMany
    @JoinTable(
            name = "possede",
            joinColumns = @JoinColumn(name = "id_joueur"),
            inverseJoinColumns = @JoinColumn(name = "id_bateau")
    )
    private List<Bateau> bateaux;

    public Joueur() {}
    public Joueur(int id, String pseudo, List<Projectile> munitions, List<Bateau> bateaux) {
        this.id = id;
        this.pseudo = pseudo;
        this.munitions = munitions;
        this.bateaux = bateaux;
    }



    public int tirer(int position_x, int position_y, Carte map, List<Projectile> munitions, int munitionUtilisee) {
        int resultat = 0;

        // Vérifie les limites de la grille pour éviter une sortie de tableau
        if (position_x < 0 || position_x >= 10 || position_y < 0 || position_y >= 10) {
            System.out.println("Tir en dehors de la carte !");
            return resultat;
        }

        Cellule cellule = map.getGrille()[position_x][position_y];

        if (munitionUtilisee == 1) { // Munition basique
            if (cellule.getBateauOccupe() != null) {
                Bateau bateau = cellule.getBateauOccupe();
                bateau.vie--;  // Réduction de la vie directement sans setter

                if (bateau.vie <= 0) {
                    map.Toucher(map.getGrille(), position_x, position_y);
                    resultat = 2; // Coulé
                } else {
                    map.Toucher(map.getGrille(), position_x, position_y);
                    resultat = 1; // Touché
                }
            }
        } else if (munitionUtilisee == 2 && munitions.get(1).getQuantite() > 0) { // Bombe
            munitions.get(1).setQuantite(munitions.get(1).getQuantite() - 1);
            resultat = 3; // Explosion en fragments
        } else if (munitionUtilisee == 3 && munitions.get(2).getQuantite() > 0) { // Autre type de munition (ex. bombe à fragmentation)
            munitions.get(2).setQuantite(munitions.get(2).getQuantite() - 1);
            tirBombeFragments(position_x, position_y, map);
            resultat = 3;
        }

        return resultat;
    }



    public void tirBombeFragments(int x, int y, Carte map) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                int posX = x + dx;
                int posY = y + dy;
                if (posX >= 0 && posX < 10 && posY >= 0 && posY < 10) {
                    tirer(posX, posY, map, munitions, 1); // Utilise le projectile classique pour chaque fragment
                }
            }
        }
    }


}
