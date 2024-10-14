-- Insertion dans la table Joueur
INSERT INTO joueur (id_joueur, pseudo) VALUES (1, 'Matuidi');
INSERT INTO joueur (id_joueur, pseudo) VALUES (2, 'RAKO');

-- Insertion dans la table Carte
INSERT INTO carte (id, nom_carte) VALUES (1, 'Mer baltique');

-- Insertion dans la table Bateau
INSERT INTO bateau (id_bateau, type_bateau, taille) VALUES (1, 'Spanish', 2);
INSERT INTO bateau (id_bateau, type_bateau, taille) VALUES (2, 'German', 5);
INSERT INTO bateau (id_bateau, type_bateau, taille) VALUES (3, 'Titanic', 3);
INSERT INTO bateau (id_bateau, type_bateau, taille) VALUES (4, 'Victoria', 4);
INSERT INTO bateau (id_bateau, type_bateau, taille) VALUES (5, 'Cruiser', 6);

-- Insertion dans la table Projectile
-- Exemple d'insertion, tu peux adapter en fonction de tes besoins
INSERT INTO projectile (id_projectile, type_projectile) VALUES (1, 'Cannonball');
INSERT INTO projectile (id_projectile, type_projectile) VALUES (2, 'Torpedo');

-- Insertion dans la table Possede (relation entre bateau et joueur)
INSERT INTO possede (id_bateau, id_joueur) VALUES (1, 1); -- Le joueur 'Matuidi' possède le bateau 'Spanish'
INSERT INTO possede (id_bateau, id_joueur) VALUES (2, 1); -- Le joueur 'Matuidi' possède le bateau 'German'
INSERT INTO possede (id_bateau, id_joueur) VALUES (3, 2); -- Le joueur 'RAKO' possède le bateau 'Titanic'

-- Insertion dans la table Contient (relation entre projectile et joueur)
INSERT INTO contient (id_projectile, id_joueur) VALUES (1, 1); -- Le joueur 'Matuidi' a un projectile de type 'Cannonball'
INSERT INTO contient (id_projectile, id_joueur) VALUES (2, 2); -- Le joueur 'RAKO' a un projectile de type 'Torpedo'
