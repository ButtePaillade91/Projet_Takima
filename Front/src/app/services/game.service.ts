import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Define your models/interfaces
export interface Joueur {
  id?: number;
  pseudo: string;
  munitions?: Projectile[];
  bateaux?: Bateau[];
}

export interface Carte {
  id?: number;
  grille?: Cellule[][];
}

export interface Bateau {
  id?: number;
  type: string;
  longueur: number;
  vie: number;
}

export interface Projectile {
  id?: number;
  type: string;
  quantite: number;
}

export interface Cellule {
  x: number;
  y: number;
  bateauOccupe?: Bateau | null;
}

@Injectable({
  providedIn: 'root',
})
export class GameService {
  private baseUrl = 'http://localhost:5432'; // Update with your backend URL

  constructor(private http: HttpClient) {}

  /** GameController Methods */

  // Start a new game
  startGame(joueur: Joueur): Observable<string> {
    const url = `${this.baseUrl}/game/start`;
    return this.http.post<string>(url, joueur);
  }

  // Trigger a player's turn (custom endpoint)
  playerTurn(
    positionX: number,
    positionY: number,
    munitionType: number
  ): Observable<string> {
    const url = `${this.baseUrl}/game/player-turn`;
    const params = {
      positionX: positionX.toString(),
      positionY: positionY.toString(),
      munitionType: munitionType.toString(),
    };
    return this.http.post<string>(url, {}, { params });
  }

  // Trigger a computer's turn (custom endpoint)
  computerTurn(): Observable<string> {
    const url = `${this.baseUrl}/game/computer-turn`;
    return this.http.post<string>(url, {});
  }

  // Get the current state of the game
  getGameState(): Observable<{ joueur: Joueur; ordinateur: Joueur; cartes: Carte[] }> {
    const url = `${this.baseUrl}/game/state`;
    return this.http.get<{ joueur: Joueur; ordinateur: Joueur; cartes: Carte[] }>(url);
  }

  /** JoueurController Methods */

  getAllJoueurs(): Observable<Joueur[]> {
    const url = `${this.baseUrl}/joueurs`;
    return this.http.get<Joueur[]>(url);
  }

  getJoueurById(id: number): Observable<Joueur> {
    const url = `${this.baseUrl}/joueurs/${id}`;
    return this.http.get<Joueur>(url);
  }

  createJoueur(joueur: Joueur): Observable<Joueur> {
    const url = `${this.baseUrl}/joueurs`;
    return this.http.post<Joueur>(url, joueur);
  }

  deleteJoueur(id: number): Observable<void> {
    const url = `${this.baseUrl}/joueurs/${id}`;
    return this.http.delete<void>(url);
  }

  tirer(
    id: number,
    positionX: number,
    positionY: number,
    carte: Carte,
    munitions: Projectile[],
    munitionUtilisee: number
  ): Observable<number> {
    const url = `${this.baseUrl}/joueurs/${id}/tirer`;
    const params = {
      positionX: positionX.toString(),
      positionY: positionY.toString(),
      munitionUtilisee: munitionUtilisee.toString(),
    };
    return this.http.post<number>(url, { carte, munitions }, { params });
  }

  /** BateauController Methods */

  getAllBateaux(): Observable<Bateau[]> {
    const url = `${this.baseUrl}/bateaux`;
    return this.http.get<Bateau[]>(url);
  }

  getBateauById(id: number): Observable<Bateau> {
    const url = `${this.baseUrl}/bateaux/${id}`;
    return this.http.get<Bateau>(url);
  }

  createBateau(bateau: Bateau): Observable<Bateau> {
    const url = `${this.baseUrl}/bateaux`;
    return this.http.post<Bateau>(url, bateau);
  }

  deleteBateau(id: number): Observable<void> {
    const url = `${this.baseUrl}/bateaux/${id}`;
    return this.http.delete<void>(url);
  }

  /** CarteController Methods */

  getAllCartes(): Observable<Carte[]> {
    const url = `${this.baseUrl}/cartes`;
    return this.http.get<Carte[]>(url);
  }

  getCarteById(id: number): Observable<Carte> {
    const url = `${this.baseUrl}/cartes/${id}`;
    return this.http.get<Carte>(url);
  }

  createCarte(carte: Carte): Observable<Carte> {
    const url = `${this.baseUrl}/cartes`;
    return this.http.post<Carte>(url, carte);
  }

  deleteCarte(id: number): Observable<void> {
    const url = `${this.baseUrl}/cartes/${id}`;
    return this.http.delete<void>(url);
  }
}
