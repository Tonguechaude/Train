package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;

public class Decharge extends CarteRouge {
    public Decharge() {
        super("Décharge", 2, 0);
    }

    public void jouer (Joueur joueur) {joueur.removeFerraille(-1);}



}
