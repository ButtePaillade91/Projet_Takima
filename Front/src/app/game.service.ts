import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private apiUrl = 'http://localhost:3000/api/generate-ships'; // URL de l'API du back-end

  constructor(private http: HttpClient) {}

  // Méthode pour obtenir les positions des bateaux
  getShips(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
