package com.takima.backskeleton.models;


import jakarta.persistence.*;

@Entity
@Table(name = "bateau")
public class Bateau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int taille;

    @ManyToOne
    @JoinColumn(name = "id_joueur", nullable = false)
    private Joueur joueur;

    int orientation;
    int vie;
    String type_bateau;

    public Bateau() {}
    public Bateau(int id, int taille, int vie, String type_bateau) {
        this.id = id;
        this.taille = taille;
        this.vie = vie;
        this.type_bateau = type_bateau;
    }


}
