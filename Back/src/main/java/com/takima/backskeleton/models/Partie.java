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

    public void nouvellePartie (String pseudo1, String pseudo2) {
        grilleDeJeu = new Carte(0, "La MAP", new Cellule[100]);

        Projectile[] munitionsJ1 = new Projectile[3];
        munitionsJ1[0] = new Projectile(0, 100, "Missile");
        munitionsJ1[1] = new Projectile(1, 1, "Torpille");
        munitionsJ1[2] = new Projectile(2, 2, "Bombe à fragments");
        Projectile[] munitionsJ2 = munitionsJ1;

        joueurs = new Joueur[2];
        joueurs[0] = new Joueur(0, pseudo1, munitionsJ1);
        joueurs[1] = new Joueur(1, pseudo2, munitionsJ2);
    }
}