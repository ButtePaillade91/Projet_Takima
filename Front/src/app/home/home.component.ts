import { Component } from '@angular/core';
import { GameService, Joueur, Carte, Projectile } from '../services/game.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  // Propriétés pour stocker les coordonnées de la cellule cliquée
  lastClicked: { x: number; y: number } | null = null;

  // Joueur et munitions
  joueur: Joueur = { pseudo: 'Joueur1' };
  munitions: Projectile[] = [{ type: 'missile', quantite: 5 }];
  carte: Carte = { grille: [] };

  // Variable pour contrôler l'affichage des pseudos
  showNames: boolean = false;

  // Propriétés pour la modale
  isModalOpen = false;
  pseudo: string = '';

  constructor(private gameService: GameService) {}

  // Méthode basique pour gérer les clics sur une cellule et effectuer un tir
  handleClick(i: number, j: number): void {
    console.log(`Cellule cliquée : Ligne ${i}, Colonne ${j}`);
    this.lastClicked = { x: i, y: j };

    // Appeler la fonction tirer() du service GameService
    const munitionType = 0; // Par exemple, le premier type de munition
    this.gameService
      .tirer(this.joueur.id || 1, i, j, this.carte, this.munitions, munitionType)
      .subscribe({
        next: (result: any) => {
          console.log('Tir effectué avec succès:', result);
        },
        error: (err: any) => {
          console.error('Erreur lors du tir:', err);
        },
      });
  }

  // Ouvrir la modale pour entrer un pseudo
  openModal(): void {
    this.isModalOpen = true;
  }

  // Fermer la modale
  closeModal(): void {
    this.isModalOpen = false;
  }

  // Commencer la partie, afficher les pseudos et fermer la modale
  startGame(): void {
    if (this.pseudo) {
      this.joueur.pseudo = this.pseudo;
      this.showNames = true; // Affiche les pseudos une fois le jeu lancé
      console.log('Pseudo du joueur:', this.joueur.pseudo);
      this.closeModal();
    } else {
      alert('Veuillez entrer un pseudo.');
    }
  }
}
