package com.takima.backskeleton.services;  // Vérifiez que c'est bien le bon package

import com.takima.backskeleton.models.Bateau;
import com.takima.backskeleton.models.Carte;
import com.takima.backskeleton.models.Joueur;
import org.springframework.stereotype.Service;
import lombok.Setter;
import lombok.Getter;

import java.util.Random;
import java.util.Scanner;

@Setter
@Getter
@Service
public class GameService {

    private Joueur joueur;
    private Joueur ordinateur;
    private Carte carteJoueur;
    private Carte carteOrdinateur;


    public GameService(Joueur joueur, Joueur ordinateur, Carte carteJoueur, Carte carteOrdinateur) {
        this.joueur = joueur;
        this.ordinateur = ordinateur;
        this.carteJoueur = carteJoueur;
        this.carteOrdinateur = carteOrdinateur;
    }

    public void startGame() {
        System.out.println("La bataille commence !");

        shipPlacement();

        // Boucle de jeu où le joueur et l'ordinateur s'alternent
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

    // La méthode playerTurn() prend maintenant les entrées de l'utilisateur directement
    public boolean playerTurn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Votre tour: Entrez les coordonnées (x, y) et le type de munition :");

        System.out.print("Coordonnée x: ");
        int x = scanner.nextInt();

        System.out.print("Coordonnée y: ");
        int y = scanner.nextInt();

        System.out.print("Type de munition: ");
        int munitionType = scanner.nextInt();

        // Appel de la méthode tir avec les entrées de l'utilisateur
        int result = joueur.tirer(x, y, carteOrdinateur, joueur.getMunitions(), munitionType);
        displayShotResult(result);

        return checkVictory(carteOrdinateur); // Vérifie si l'ordinateur a perdu
    }

    public boolean computerTurn() {
        System.out.println("Tour de l'ordinateur...");

        // Logique pour déterminer les coordonnées du tir de l'ordinateur
        int x = new Random().nextInt(10); // Coordonnée aléatoire entre 0 et 9
        int y = new Random().nextInt(10); // Coordonnée aléatoire entre 0 et 9
        int munitionType = 1; // Exemple de type de munition

        int result = ordinateur.tirer(x, y, carteJoueur, ordinateur.getMunitions(), munitionType);
        displayShotResult(result);

        return checkVictory(carteJoueur); // Vérifie si le joueur a perdu
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
        // Vérifie si tous les bateaux ont été coulés
        for (int i = 0; i < carte.grille.length; i++) {
            for (int j = 0; j < carte.grille[i].length; j++) {
                if (carte.grille[i][j].bateauOccupe != null) {
                    return false; // Il reste des bateaux, donc pas de victoire
                }
            }
        }
        return true; // Tous les bateaux sont coulés, victoire
    }

    public void shipPlacement() {
        Random rand = new Random();
        int[] taillesBateaux = {5, 4, 3, 3, 2};

        for (int i = 0; i < taillesBateaux.length; i++) {
            Bateau bateau = new Bateau();
            bateau.setTaille(taillesBateaux[i]);
            bateau.setVie(taillesBateaux[i]);
            bateau.setType_bateau("Bateau" + (i + 1));

            boolean bateauPlace = false;
            while (!bateauPlace) {
                int x = rand.nextInt(10);
                int y = rand.nextInt(10);
                int orientation = rand.nextInt(2);

                if (isValidPlacement(x, y, orientation, bateau.getTaille(), carteOrdinateur)) {
                    placeShip(x, y, orientation, bateau, carteOrdinateur);
                    bateauPlace = true;
                }
            }
            bateau.setJoueur(joueur);
        }
    }

    private boolean isValidPlacement(int x, int y, int orientation, int taille, Carte carte) {
        if (orientation == 0) {
            if (x + taille > 10) return false;
            for (int i = 0; i < taille; i++) {
                if (carte.grille[x + i][y].bateauOccupe != null) {
                    return false;
                }
            }
        } else {
            if (y + taille > 10) return false;
            for (int i = 0; i < taille; i++) {
                if (carte.grille[x][y + i].bateauOccupe != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private void placeShip(int x, int y, int orientation, Bateau bateau, Carte carte) {
        if (orientation == 0) {
            for (int i = 0; i < bateau.getTaille(); i++) {
                carte.grille[x + i][y].bateauOccupe = bateau;
            }
        } else {
            for (int i = 0; i < bateau.getTaille(); i++) {
                carte.grille[x][y + i].bateauOccupe = bateau;
            }
        }
    }
}