import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Define your models/interfaces
export interface Joueur {
  id?: number;
  pseudo: string;
}

export interface Carte {
  id?: number;
  grille?: Cellule[][];
}

export interface Bateau {
  id?: number;
  type_bateau: string;
  taille: number;
  vie: number;
}

export interface Projectile {
  id?: number;
  type: string;
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
  private baseUrl = 'http://localhost:8080/api'; // Update with your backend URL

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

  ): Observable<string> {
    const url = `${this.baseUrl}/game/player-turn`;
    const params = {
      positionX: positionX.toString(),
      positionY: positionY.toString(),

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
    const url = `${this.baseUrl}/joueur/all`;
    return this.http.get<Joueur[]>(url);
  }

  getJoueurById(id: number): Observable<Joueur> {
    const url = `${this.baseUrl}/joueur/${id}`;
    return this.http.get<Joueur>(url);
  }

  createJoueur(joueur: Joueur): Observable<Joueur> {
    const url = `${this.baseUrl}/joueur/create`;
    return this.http.post<Joueur>(url, joueur);
  }

  deleteJoueur(id: number): Observable<void> {
    const url = `${this.baseUrl}/joueur/delete/${id}`;
    return this.http.delete<void>(url);
  }

  tirer(
    id: number,
    positionX: number,
    positionY: number,
    carte: Carte
  ): Observable<number> {
    const url = `${this.baseUrl}/joueur/${id}/tirer`;
    const params = {
      positionX: positionX.toString(),
      positionY: positionY.toString(),

    };
    return this.http.post<number>(url, { carte}, { params });
  }

  /** BateauController Methods */

  getAllBateaux(): Observable<Bateau[]> {
    const url = `${this.baseUrl}/bateaux/all`;
    return this.http.get<Bateau[]>(url);
  }

  getBateauById(id: number): Observable<Bateau> {
    const url = `${this.baseUrl}/bateaux/${id}`;
    return this.http.get<Bateau>(url);
  }

  createBateau(bateau: Bateau): Observable<Bateau> {
    const url = `${this.baseUrl}/bateaux/create`;
    return this.http.post<Bateau>(url, bateau);
  }

  deleteBateau(id: number): Observable<void> {
    const url = `${this.baseUrl}/bateaux/delete/${id}`;
    return this.http.delete<void>(url);
  }

  /** CarteController Methods */

  getAllCartes(): Observable<Carte[]> {
    const url = `${this.baseUrl}/carte/all`;
    return this.http.get<Carte[]>(url);
  }

  getCarteById(id: number): Observable<Carte> {
    const url = `${this.baseUrl}/carte/${id}`;
    return this.http.get<Carte>(url);
  }

  createCarte(carte: Carte): Observable<Carte> {
    const url = `${this.baseUrl}/carte/carte`;
    return this.http.post<Carte>(url, carte);
  }

  deleteCarte(id: number): Observable<void> {
    const url = `${this.baseUrl}/carte/delete/${id}`;
    return this.http.delete<void>(url);
  }

  /** ProjectileController Methods */

  getAllProjectiles(): Observable<Projectile[]> {
    const url = `${this.baseUrl}/projectile/all`;
    return this.http.get<Projectile[]>(url);
  }

  getProjectileById(id: number): Observable<Projectile> {
    const url = `${this.baseUrl}/projectile/${id}`;
    return this.http.get<Projectile>(url);
  }

  createProjectile(projectile: Projectile): Observable<Projectile> {
    const url = `${this.baseUrl}/projectile/create`;
    return this.http.post<Projectile>(url, projectile);
  }

  deleteProjectile(id: number): Observable<void> {
    const url = `${this.baseUrl}/projectile/delete/${id}`;
    return this.http.delete<void>(url);
  }
}
