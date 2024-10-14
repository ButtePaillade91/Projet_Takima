create table Joueur
(
    id SERIAL PRIMARY KEY,
    Pseudo TEXT not null
);

create table Carte
(
    id SERIAL PRIMARY KEY,
    nom TEXT not null,
    description TEXT not null
);

create table Bateau
(
    id SERIAL PRIMARY KEY,
    nom TEXT not null,
    taille int not null
);
