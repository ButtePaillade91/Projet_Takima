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

  async playGame(): Promise<void> {
    let gameEnded = false;

    console.log('Le jeu commence !');

    while (!gameEnded) {
      // Player's turn
      await this.waitForPlayerTurn();

      gameEnded = await this.checkVictory(this.carte);
      if (gameEnded) {
        console.log('Game Over! Player wins!');
        break;
      }

      // Computer's turn
      await this.computerTurn(this.carteAdversaire);

      gameEnded = await this.checkVictory(this.carteAdversaire);
      if (gameEnded) {
        console.log('Game Over! Computer wins!');
        break;
      }
    }
  }


  async waitForPlayerTurn(): Promise<void> {
    // Wait for a player action (e.g., clicking a cell on the game board)
    return new Promise((resolve) => {
      // Add an event listener or hook into your UI logic
      const clickListener = (event: any) => {
        const i = event.detail.row; // Example: Row index from event details
        const j = event.detail.column; // Example: Column index from event details

        // Ensure the event calls handleClick and resolves only when a real move happens
        this.handleClick(i, j)
          .then(() => {
            document.removeEventListener('playerClick', clickListener); // Cleanup listener
            resolve(); // Resolve after the player's turn is completed
          })
          .catch((error) => {
            console.error('Error during player turn:', error);
            document.removeEventListener('playerClick', clickListener); // Cleanup on error
          });
      };

      // Listen for a custom event fired by your UI (e.g., a grid click)
      document.addEventListener('playerClick', clickListener);
    });
  }


  computerTurn(carte: Carte): Promise<void> {
    return new Promise((resolve, reject) => {
      this.gameService.computerTurn(carte).subscribe({
        next: (result: any) => {
          console.log('Computer turn completed:', result);
          resolve();
        },
        error: (err: any) => {
          console.error('Error during computer turn:', err);
          reject(err);
        },
      });
    });
  }

  checkVictory(carte: Carte): Promise<boolean> {
    return new Promise((resolve, reject) => {
      this.gameService.checkVictory(carte).subscribe({
        next: (result: any) => {
          console.log('Check effectué avec succès', result);
          if (result === 'victory') {
            resolve(true);
          } else {
            resolve(false);
          }
        },
        error: (err: any) => {
          console.error('Erreur lors du check de victoire:', err);
          reject(err);
        },
      });
    });
  }



  startGame(): void {
    // Validate player selection
    if (!this.selectedJoueurId && !this.pseudo) {
      alert('Veuillez choisir un joueur ou entrer un pseudo.');
      return;
    }

    // Step 1: Manage the player
    const playerPromise = new Promise<void>((resolve, reject) => {
      if (this.selectedJoueurId) {
        this.joueur = this.joueurs.find(j => j.id === this.selectedJoueurId)!;
        resolve(); // Player already selected
      } else {
        const newJoueur: Joueur = { pseudo: this.pseudo };
        this.gameService.createJoueur(newJoueur).subscribe(
          (data) => {
            this.joueur = data;
            this.loadJoueurs();
            resolve();
          },
          (error) => {
            console.error('Erreur lors de la création du joueur', error);
            reject(error);
          }
        );
      }
    });

    // Step 2: Fetch the carte and setup ships
    const setupPromise = new Promise<void>((resolve, reject) => {
      this.gameService.getCarteById(1).subscribe(
        (data) => {
          this.carte = data;
          this.carteAdversaire = JSON.parse(JSON.stringify(data)); // Clone for the opponent
          this.showNames = true;
          this.closeModal();

          // Place ships for both players
          const playerShipPlacement = this.gameService.shipPlacement(this.carte, this.bateaux).toPromise();
          const adversaryShipPlacement = this.gameService.shipPlacement(this.carteAdversaire, this.bateaux).toPromise();

          Promise.all([playerShipPlacement, adversaryShipPlacement])
            .then(() => {
              console.log('Bateaux placés avec succès');
              resolve();
            })
            .catch((error) => {
              console.error('Erreur lors du placement des bateaux', error);
              reject(error);
            });
        },
        (error) => {
          console.error('Erreur lors du chargement de la carte', error);
          reject(error);
        }
      );
    });

    // Step 3: Start the game after setup is complete
    Promise.all([playerPromise, setupPromise])
      .then(() => {
        console.log('Démarrage du jeu...');
        this.playGame().then(() => {
          console.log('Le jeu est terminé.');
        });
      })
      .catch((error) => {
        console.error('Erreur lors de la configuration du jeu', error);
      });
  }


}
