import { Component, OnInit } from "@angular/core";

@Component({
  selector: "home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  gameStarted: boolean = false;
  currentPlayer: number = 1; // 1 pour Joueur 1, 2 pour Joueur 2
  isPlacing: boolean = true; // Pour savoir si le joueur est en mode placement
  overlayOnPlayer1: boolean = false; // Overlay pour Joueur 1
  overlayOnPlayer2: boolean = false; // Overlay pour Joueur 2

  handleClick(row: number, col: number) {
    if (this.isPlacing) {
      console.log(`Placing boat: Row ${row}, Column ${col}`);
      // Logique pour placer un bateau
    } else {
      console.log(`Cell clicked: Row ${row}, Column ${col}`);
      // Logique pour le jeu (par exemple, marquer un coup)
    }
  }

  startGame() {
    this.gameStarted = true;
  }

  validatePlacement() {
    // Change de joueur et applique l'overlay sur l'autre joueur
    if (this.currentPlayer === 1) {
      this.overlayOnPlayer1 = true;  // Overlay sur Joueur 1
      this.overlayOnPlayer2 = false; // Pas d'overlay sur Joueur 2
      this.currentPlayer = 2;
    } else {
      this.overlayOnPlayer2 = true;  // Overlay sur Joueur 2
      this.overlayOnPlayer1 = false; // Pas d'overlay sur Joueur 1
      this.currentPlayer = 1;
    }

    this.isPlacing = !this.isPlacing; // Basculer le mode placement
  }

  constructor() {}

  ngOnInit(): void {}
}
