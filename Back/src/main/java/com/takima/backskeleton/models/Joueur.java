package com.takima.backskeleton.models;

import jakarta.persistence.*;

import java.util.List;

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



    public int tirer (int position_x, int position_y, Carte map, List<Projectile> munitions, int munitionUtilisee) {
        int resultat = 0;
        int cellule = position_y*10+position_x;

        if (munitionUtilisee==1){
            if (map.grille[cellule].bateauOccupe!=null) {
                map.grille[cellule].bateauOccupe.vie--;
                if (map.grille[cellule].bateauOccupe.vie==0) {
                    map.Toucher(map.grille, cellule);
                    resultat=2;
                    return resultat;//coulé
                } else {
                    map.Toucher(map.grille, cellule);
                    resultat=1;
                    return resultat;//touché
                }
            } else {
                return resultat;//dans l'eau
            }
        } else if (munitionUtilisee==2) {
            munitions.get(1).quantite--;
            return resultat;
        } else {
            munitions.get(2).quantite--;
            tirBombeFragments(position_x, position_y, map);
            resultat=3;
            return resultat;
        }
    }

    public void tirBombeFragments(int x, int y, Carte map) {
        tirer(x-1, y-1, map, munitions, 1);
        tirer(x, y-1, map, munitions, 1);
        tirer(x+1, y-1, map, munitions, 1);
        tirer(x-1, y, map, munitions, 1);
        tirer(x, y, map, munitions, 1);
        tirer(x+1, y, map, munitions, 1);
        tirer(x-1, y+1, map, munitions, 1);
        tirer(x, y+1, map, munitions, 1);
        tirer(x+1, y+1, map, munitions, 1);
    }
}
