import { Component, OnInit } from "@angular/core"
import { GameService, Joueur, Carte, Projectile, Bateau } from "../services/game.service"

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit{

  // List of available players
  joueurs: Joueur[] = [];
  selectedJoueurId: number | null = null;

  cartes: Carte[] = [];
  selectedCarteId: number | null = null;

  // List to hold the Bateau objects
  bateaux: Bateau[] = [
    { id: 0, type_bateau: '', taille: 0, vie: 0 },
    { id: 1, type_bateau: '', taille: 0, vie: 0 },
    { id: 2, type_bateau: '', taille: 0, vie: 0 },
    { id: 3, type_bateau: '', taille: 0, vie: 0 },
    { id: 4, type_bateau: '', taille: 0, vie: 0 },
  ];

  // Player data
  joueur: Joueur = { pseudo: '' };
  pseudo: string = '';
  ordinateur : Joueur = {pseudo:''};

  fetchBateau(id: number): void {
    // Fetch the Bateau object based on its ID
    this.gameService.getBateauById(id).subscribe(
      (data) => {
        // Find the correct Bateau in the list and update it
        const bateauToUpdate = this.bateaux.find(bateau => bateau.id === id);
        if (bateauToUpdate) {
          bateauToUpdate.type_bateau = data.type_bateau;
          bateauToUpdate.taille = data.taille;
          bateauToUpdate.vie = data.vie;
        }
        console.log(`Bateau data for bateau ${id}:`, bateauToUpdate);
      },
      (error) => {
        console.error(`Error fetching bateau ${id}:`, error);
      }
    );
  }

  // Propriétés pour stocker les coordonnées de la cellule cliquée
  lastClicked: { x: number; y: number } | null = null;

  // Joueur et munitions

  carte: Carte = { nom_carte: '' };
  carteAdversaire: Carte = { nom_carte: '' }; // Separate Carte for the opponent
  nom_carte: string ='';

  loadJoueurs(): void {
    this.gameService.getAllJoueurs().subscribe(
      (data) => {
        this.joueurs = data;
        console.log('Loaded joueurs:', this.joueurs);
      },
      (error) => {
        console.error('Error loading joueurs:', error);
      }
    );
  }

  loadCartes(): void {
    this.gameService.getAllCartes().subscribe(
      (data) => {
        this.cartes = data;
        console.log('Loaded cartes:', this.cartes);
      },
      (error) => {
        console.error('Error loading cartes:', error);
      }
    );
  }

  ngOnInit(): void {
    this.loadJoueurs();
    this.loadCartes();
  }

  // Propriétés pour la modale
  isModalOpen = false;
  // Variable pour contrôler l'affichage des pseudos
  showNames: boolean = false;

  constructor(private gameService: GameService) {}

  // Méthode basique pour gérer les clics sur une cellule et effectuer un tir
  handleClick(i: number, j: number): Promise<void> {
    console.log(`Cellule cliquée : Ligne ${i}, Colonne ${j}`);
    this.lastClicked = { x: i, y: j };

    return new Promise((resolve, reject) => {
      // Appeler la fonction tirer() du service GameService
      this.gameService.tirer(i, j, this.carteAdversaire).subscribe({
        next: (result: any) => {
          console.log('Tir effectué avec succès:', result);
          resolve(); // Resolving the promise when the player's turn is complete
        },
        error: (err: any) => {
          console.error('Erreur lors du tir:', err);
          reject(err); // Reject if there is an error
        },
      });
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

  startGame(): void {
    for (let i = 0; i <= 4; i++) {
      this.fetchBateau(i);
    }
    if (!this.selectedJoueurId && !this.pseudo) {
      alert('Veuillez choisir un joueur ou entrer un pseudo.');
      return;
    }

    // Manage the player
    if (this.selectedJoueurId) {
      this.joueur = this.joueurs.find(j => j.id === this.selectedJoueurId)!;
    } else {
      const newJoueur: Joueur = { pseudo: this.pseudo };
      this.gameService.createJoueur(newJoueur).subscribe((data) => {
        this.joueur = data;
        this.loadJoueurs();
      });
    }

    // Fetch the default Carte and start the game
    this.gameService.getCarteById(1).subscribe(
      (data) => {
        this.carte = data;
        this.carteAdversaire = JSON.parse(JSON.stringify(data)); // Clone for the opponent
        this.showNames = true;
        this.closeModal();
      },
      (error) => {
        console.error('Erreur lors du chargement de la carte', error);
      }
    );
    this.gameService.shipPlacement(this.carte, this.bateaux).subscribe(response => {

        console.log('Bateaux placés avec succès:', response);
    },
      (error) => {
        console.error('Erreur lors du chargement des bateaux', error);
     });
    this.gameService.shipPlacement(this.carteAdversaire, this.bateaux).subscribe(response => {

        console.log('Bateaux placés avec succès:', response);
      },
      (error) => {
        console.error('Erreur lors du chargement des bateaux', error);
      });

  }

}
