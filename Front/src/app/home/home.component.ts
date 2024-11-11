import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  // Méthode basique pour gérer les clics sur une cellule
  handleClick(i: number, j: number): void {
    console.log(`Cellule cliquée : Ligne ${i}, Colonne ${j}`);
  }
}
