package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.TypeTerrain;

public class PontEnAcier extends CarteVerte {
    public PontEnAcier() {
        super("Pont en acier", 4);
    }
    // A FINIR POUR GERER REDUCTION SUR LES FLEUVES
    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.addReduction(TypeTerrain.FLEUVE);
    }
}
