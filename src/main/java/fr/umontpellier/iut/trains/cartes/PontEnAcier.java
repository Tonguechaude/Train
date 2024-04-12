package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class PontEnAcier extends CarteVerte {
    public PontEnAcier() {
        super("Pont en acier", 4);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.addPointDeRails(1);
        joueur.addFerraille(1);
        //joueur.addReduction("Rivière");
        joueur.getJeu().log(String.format("%s reçoit 1 point de rail et 1 carte Ferraille ainsi qu'une réduction pour poser des rails sur les tuiles rivière durant ce tour uniquement", joueur.getNom()));
    }
}
