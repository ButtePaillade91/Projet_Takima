package com.takima.backskeleton.models;
public class Cellule {
    int id;
    int position_x;
    int position_y;
    Bateau bateauOccupe;

    public Cellule(int id, int position_x, int position_y, Bateau bateauOccupe) {
        this.id = id;
        this.position_x = position_x;
        this.position_y = position_y;
        this.bateauOccupe = bateauOccupe;
    }
}
