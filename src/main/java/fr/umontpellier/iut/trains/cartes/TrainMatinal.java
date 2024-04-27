package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainMatinal extends CarteDoubleType {
    public TrainMatinal() {
        super("Train matinal",5,2, "Train", "Action");
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.getListCarteSpeciales().add(getNom());
    }
}
