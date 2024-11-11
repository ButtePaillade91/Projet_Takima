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

        shipPlacement();

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

    public void shipPlacement() {
        Random rand = new Random();

        // Liste de tailles de bateaux à placer (exemple)
        int[] taillesBateaux = {5, 4, 3, 3, 2}; // Par exemple, 5, 4, 3, 3, 2 unités de taille pour les bateaux

        for (int i = 0; i < taillesBateaux.length; i++) {
            Bateau bateau = new Bateau();
            bateau.setTaille(taillesBateaux[i]);
            bateau.setVie(taillesBateaux[i]); // La vie d'un bateau est égale à sa taille
            bateau.setType_bateau("Bateau" + (i + 1)); // Type de bateau pour l'exemple

            // Tentative de placement du bateau
            boolean bateauPlace = false;
            while (!bateauPlace) {
                // Choisir une position aléatoire pour le bateau
                int x = rand.nextInt(10); // X entre 0 et 9
                int y = rand.nextInt(10); // Y entre 0 et 9
                int orientation = rand.nextInt(2); // 0 = horizontal, 1 = vertical

                // Vérifiez si l'emplacement est valide
                if (isValidPlacement(x, y, orientation, bateau.getTaille(), carteOrdinateur)) {
                    // Placez le bateau sur la carte
                    placeShip(x, y, orientation, bateau, carteOrdinateur);
                    bateauPlace = true; // Si le placement est valide, on arrête la boucle
                }
            }

            // Associer le bateau à un joueur (par exemple, joueur ou ordinateur)
            bateau.setJoueur(joueur); // Associez le bateau à l'utilisateur
            // Vous pouvez aussi ajouter un moyen d'associer ces bateaux à l'ordinateur
        }
    }
    private boolean isValidPlacement(int x, int y, int orientation, int taille, Carte carte) {
        // Vérifiez que le bateau reste dans les limites de la carte
        if (orientation == 0) { // horizontal
            if (x + taille > 10) return false; // Hors des limites
            for (int i = 0; i < taille; i++) {
                if (carte.grille[x + i][y].bateauOccupe != null) {
                    return false; // Si une cellule est déjà occupée, placement invalide
                }
            }
        } else { // vertical
            if (y + taille > 10) return false; // Hors des limites
            for (int i = 0; i < taille; i++) {
                if (carte.grille[x][y + i].bateauOccupe != null) {
                    return false; // Si une cellule est déjà occupée, placement invalide
                }
            }
        }
        return true; // Placement valide
    }
    private void placeShip(int x, int y, int orientation, Bateau bateau, Carte carte) {
        if (orientation == 0) { // horizontal
            for (int i = 0; i < bateau.getTaille(); i++) {
                carte.grille[x + i][y].bateauOccupe = bateau;
            }
        } else { // vertical
            for (int i = 0; i < bateau.getTaille(); i++) {
                carte.grille[x][y + i].bateauOccupe = bateau;
            }
        }
    }

}
