package com.takima.backskeleton.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "bateau")
public class Bateau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_bateau;
    private int taille;
    private int vie;
    private String type_bateau;

    public Bateau() {}
    public Bateau(int id_bateau, int taille, int vie, String type_bateau) {
        this.id_bateau = id_bateau;
        this.taille = taille;
        this.vie = vie;
        this.type_bateau = type_bateau;
    }
}
