package com.takima.backskeleton.models;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PoserBateau {
    private List<Bateau> bateaux;
    private Carte carte;

    // getters and setters
}
