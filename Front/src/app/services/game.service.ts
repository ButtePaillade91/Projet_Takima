import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Define your models/interfaces (can be expanded as needed)
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
  private baseUrl = 'http://localhost:8080'; // Update with your backend URL

  constructor(private http: HttpClient) {}

  /** GameController Methods */

  startGame(joueur: Joueur): Observable<string> {
    const url = `${this.baseUrl}/game/start`;
    return this.http.post<string>(url, joueur);
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
