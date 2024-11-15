import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  // Propriété pour gérer l'état de la fenêtre modale
  isModalOpen = false;

  // Propriété pour capturer le pseudo du joueur
  pseudo: string = '';

  // Méthode basique pour gérer les clics sur une cellule
  handleClick(i: number, j: number): void {
    console.log(`Cellule cliquée : Ligne ${i}, Colonne ${j}`);
  }

  // Méthode pour ouvrir la modale
  openModal(): void {
    this.isModalOpen = true;
  }

  // Méthode pour fermer la modale
  closeModal(): void {
    this.isModalOpen = false;
  }

  // Méthode pour démarrer le jeu et envoyer le pseudo
  startGame(): void {
    if (this.pseudo) {
      console.log('Pseudo du joueur:', this.pseudo);
      // Vous pouvez envoyer ce pseudo au backend ici via un service HTTP
      // Exemple : this.gameService.startGame(this.pseudo);

      this.closeModal(); // Fermer la modale après avoir démarré le jeu
    } else {
      alert('Veuillez entrer un pseudo.');
    }
  }
}
