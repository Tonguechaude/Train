package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class PoseDeRails extends CarteVerte {
    public PoseDeRails() {
        super("Pose de rails",3);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.addPointDeRails(1);
        joueur.addFerraille(1);
    }
}
