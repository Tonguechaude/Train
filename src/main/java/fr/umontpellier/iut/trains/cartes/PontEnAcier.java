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
        joueur.addPointDeRails(1);
        joueur.addFerraille(1);
        joueur.addReduction(TypeTerrain.FLEUVE);
        joueur.log("Vous recevez 1 point de rail et 1 carte Ferraille ainsi qu'une réduction pour poser des rails sur les tuiles rivière durant ce tour uniquement.");
    }
}
