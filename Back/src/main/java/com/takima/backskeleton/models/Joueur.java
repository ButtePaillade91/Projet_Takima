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


}
