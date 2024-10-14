DROP TABLE IF EXISTS public.possede;
DROP TABLE IF EXISTS public.contient;
DROP TABLE IF EXISTS public.joueur;
DROP TABLE IF EXISTS public.bateau;
DROP TABLE IF EXISTS public.carte;
DROP TABLE IF EXISTS public.projectile;

CREATE TABLE IF NOT EXISTS public.joueur (
                                             id_joueur   SERIAL PRIMARY KEY,
                                             pseudo      TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.bateau (
                                             id_bateau     SERIAL PRIMARY KEY,
                                             type_bateau   TEXT NOT NULL,
                                             taille        INT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.carte (
                                            id          SERIAL PRIMARY KEY,
                                            nom_carte   TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.projectile (
                                                 id_projectile     SERIAL PRIMARY KEY,
                                                 type_projectile   TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.possede (
                                              id_bateau   INT NOT NULL,
                                              id_joueur   INT NOT NULL,
                                              PRIMARY KEY (id_bateau, id_joueur),
                                              CONSTRAINT fk_possede_bateau FOREIGN KEY (id_bateau) REFERENCES public.bateau(id_bateau),
                                              CONSTRAINT fk_possede_joueur FOREIGN KEY (id_joueur) REFERENCES public.joueur(id_joueur)
);

CREATE TABLE IF NOT EXISTS public.contient (
                                               id_projectile   INT NOT NULL,
                                               id_joueur       INT NOT NULL,
                                               PRIMARY KEY (id_projectile, id_joueur),
                                               CONSTRAINT fk_contient_projectile FOREIGN KEY (id_projectile) REFERENCES public.projectile(id_projectile),
                                               CONSTRAINT fk_contient_joueur FOREIGN KEY (id_joueur) REFERENCES public.joueur(id_joueur)
);
