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

        Bateau[] bateauxJ1 = new Bateau[5];
        bateauxJ1[0] = new Bateau(0, 5, 5, "Porte-avions");
        bateauxJ1[1] = new Bateau(1, 4, 4, "Cuirassé");
        bateauxJ1[2] = new Bateau(2, 3, 3, "Sous-marin");
        bateauxJ1[3] = new Bateau(3, 3, 3, "Contre-torpilleur");
        bateauxJ1[4] = new Bateau(4, 2, 2, "Garde-côte");
        Bateau[] bateauxJ2 = bateauxJ1;

        joueurs = new Joueur[2];
        joueurs[0] = new Joueur(0, pseudo1, munitionsJ1, bateauxJ1);
        joueurs[1] = new Joueur(1, pseudo2, munitionsJ2, bateauxJ2);
    }
}