package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.TypeTerrain;

public class Viaduc extends CarteVerte {
    public Viaduc() {
        super("Viaduc",5);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.addFerraille(1);
        joueur.addPointDeRails(1);
        joueur.getListReductions().add(TypeTerrain.VILLE);
    }
}
