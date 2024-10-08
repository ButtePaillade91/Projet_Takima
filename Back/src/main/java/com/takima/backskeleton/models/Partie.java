package com.takima.backskeleton.models;
public class Partie {
    Carte grilleDeJeu;
    Joueur[] joueurs;
    Bateau[] bateaux;
    public Partie(Carte grilleDeJeu, Joueur[] joueurs, Bateau[] bateaux) {
        this.grilleDeJeu = grilleDeJeu;
        this.joueurs = joueurs;
        this.bateaux = bateaux;
    }

    public void nouvellePartie () {
        grilleDeJeu = new Carte(0, "La MAP", new Cellule[50]);
    }
}