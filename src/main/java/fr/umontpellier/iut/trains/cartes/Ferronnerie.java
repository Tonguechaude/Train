package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Ferronnerie extends CarteRouge {
    public Ferronnerie() {
        super("Ferronnerie", 4, 1);
    }

    public void jouer (Joueur joueur)
    {
        joueur.getListCarteSpeciales().add(getNom());
    }
}
