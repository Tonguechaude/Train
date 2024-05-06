package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainDeTourisme extends CarteDoubleType {
    public TrainDeTourisme() {
        super("Train de tourisme",4,1, "Train", "Action");
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.addPtVictoire(1);
    }
}
