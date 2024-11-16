package com.takima.backskeleton.services;  // Vérifiez que c'est bien le bon package

import com.takima.backskeleton.models.Bateau;
import com.takima.backskeleton.models.Carte;
import com.takima.backskeleton.models.Cellule;
import com.takima.backskeleton.models.Joueur;
import org.springframework.stereotype.Service;
import lombok.Setter;
import lombok.Getter;

import java.util.List;
import java.util.Random;

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


    public void computerTurn(Carte carteJoueur) {
        System.out.println("Tour de l'ordinateur...");
        try {
            // Logique pour déterminer les coordonnées du tir de l'ordinateur
            int x = new Random().nextInt(10); // Coordonnée aléatoire entre 0 et 9
            int y = new Random().nextInt(10); // Coordonnée aléatoire entre 0 et 9
            System.out.println("Ordinateur tire sur les coordonnées : (" + x + ", " + y + ")");

            int result = tirer(x, y, carteJoueur); // Appeler la méthode tirer

            // Optionally log the result
            System.out.println("Résultat du tir : " + result);
        } catch (Exception e) {
            System.err.println("Erreur pendant le tour de l'ordinateur : " + e.getMessage());
            e.printStackTrace();
        }
    }



    public boolean checkVictory(Carte carte) {
        // Vérifie si tous les bateaux ont été coulés
        for (int i = 0; i < carte.grille.length; i++) {
            for (int j = 0; j < carte.grille[i].length; j++) {
                if (carte.grille[i][j].bateauOccupe != null) {
                    return false; // Il reste des bateaux, donc pas de victoire
                }
            }
        }
        return true; // Tous les bateaux ont été coulés, victoire
    }

    public void shipPlacement(Carte carte, List<Bateau> bateau) {

        Random rand = new Random();

        // Place ships for both the ordinateur and the joueur
        for (int i = 0; i < 5; i++) {
            // Place ship for ordinateur
            Bateau bateauOrdinateur = bateau.get(i);

            boolean bateauOrdinateurPlace = false;
            while (!bateauOrdinateurPlace) {
                int x = rand.nextInt(10);
                int y = rand.nextInt(10);
                int orientation = rand.nextInt(2); // 0 for horizontal, 1 for vertical

                if (isValidPlacement(x, y, orientation, bateauOrdinateur.getTaille(), carte)) {
                    placeShip(x, y, orientation, bateauOrdinateur, carte);
                    bateauOrdinateurPlace = true;
                }
            }
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

    public int tirer(int position_x, int position_y, Carte map) {
        int resultat = 3; //dans l'eau

        Cellule cellule = map.getGrille()[position_x][position_y];

        // Munition basique
        if (cellule.getBateauOccupe() != null) {
            cellule.getBateauOccupe().setVie(cellule.getBateauOccupe().getVie() - 1);

            if (cellule.getBateauOccupe().getVie() <= 0) {
                map.Toucher(map.getGrille(), position_x, position_y);
                resultat = 2; // Coulé
            } else {
                map.Toucher(map.getGrille(), position_x, position_y);
                resultat = 1; // Touché
            }
        }


        return resultat;
    }

}