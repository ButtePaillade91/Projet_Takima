package com.takima.backskeleton.models;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Cellule {
    private int id;
    public Bateau bateauOccupe;

    public Cellule(int id, Bateau bateauOccupe) {
        this.id = id;
        this.bateauOccupe = bateauOccupe;
    }
}
