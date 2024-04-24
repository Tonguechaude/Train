package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.TypeTerrain;

public class Viaduc extends CarteVerte {
    public Viaduc() {
        super("Viaduc",5);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.getListReductions().add(TypeTerrain.VILLE);
    }
}
