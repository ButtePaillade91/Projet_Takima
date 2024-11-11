package com.takima.backskeleton.services;

import com.takima.backskeleton.models.*;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BatailleNavaleGame {

    private Joueur joueur;
    private Joueur ordinateur;
    private Carte carteJoueur;
    private Carte carteOrdinateur;

    public BatailleNavaleGame(Joueur joueur, Joueur ordinateur, Carte carteJoueur, Carte carteOrdinateur) {
        this.joueur = joueur;
        this.ordinateur = ordinateur;
        this.carteJoueur = carteJoueur;
        this.carteOrdinateur = carteOrdinateur;
    }

    public void startGame() {
        System.out.println("La bataille commence !");
        while (true) {
            if (playerTurn()) {
                System.out.println("Vous avez gagné !");
                break;
            }
            if (computerTurn()) {
                System.out.println("L'ordinateur a gagné !");
                break;
            }
        }
    }

    private boolean playerTurn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Votre tour : ");

        System.out.print("Entrez la position X : ");
        int x = scanner.nextInt();

        System.out.print("Entrez la position Y : ");
        int y = scanner.nextInt();

        System.out.print("Choisissez la munition (1: Basique, 2: Bombe) : ");
        int munitionType = scanner.nextInt();

        int result = joueur.tirer(x, y, carteOrdinateur, joueur.getMunitions(), munitionType);

        displayShotResult(result);
        return checkVictory(carteOrdinateur);
    }

    private boolean computerTurn() {
        System.out.println("Tour de l'ordinateur...");

        // Placeholder for AI decision-making logic
        int x = new Random().nextInt(10);
        int y = new Random().nextInt(10);
        int munitionType = 1; // Placeholder for AI munition selection

        int result = ordinateur.tirer(x, y, carteJoueur, ordinateur.getMunitions(), munitionType);

        displayShotResult(result);
        return checkVictory(carteJoueur);
    }

    private void displayShotResult(int result) {
        switch (result) {
            case 0:
                System.out.println("Dans l'eau.");
                break;
            case 1:
                System.out.println("Touché !");
                break;
            case 2:
                System.out.println("Coulé !");
                break;
            case 3:
                System.out.println("Explosion en fragment !");
                break;
            default:
                System.out.println("Résultat inconnu.");
                break;
        }
    }

    private boolean checkVictory(Carte carte) {
        for (Cellule[][] cellule : carte.grille) {
            if (cellule.bateauOccupe != null) {
                return false;
            }
        }
        return true;
    }
}
