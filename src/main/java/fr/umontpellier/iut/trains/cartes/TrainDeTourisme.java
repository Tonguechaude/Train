package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.Arrays;
import java.util.List;

public class TrainDeTourisme extends CarteBleu {
    public TrainDeTourisme() {
        super("Train de tourisme",4,1);
    }

    @Override
    public void jouer(Joueur joueur) {
            joueur.addPtVictoire(1);
    }
}
