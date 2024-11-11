package com.takima.backskeleton.models;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cellule {
    int id;
    int position_x;
    int position_y;
    public Bateau bateauOccupe;

    public Cellule(int id, int position_x, int position_y, Bateau bateauOccupe) {
        this.id = id;
        this.position_x = position_x;
        this.position_y = position_y;
        this.bateauOccupe = bateauOccupe;
    }
}
