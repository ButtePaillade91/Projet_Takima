package com.takima.backskeleton.models;
public class Cellule {
    int id;
    int position_x;
    int position_y;
    String etat;
    public Cellule(int id, int position_x, int position_y, String etat) {
        this.id = id;
        this.position_x = position_x;
        this.position_y = position_y;
        this.etat = etat;
    }
}
