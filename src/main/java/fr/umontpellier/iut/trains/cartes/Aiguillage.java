package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Aiguillage extends Carte {
    public Aiguillage() {
        super("Aiguillage",5);
    }
    // gérer le prix !!
    @Override
    public void jouer(Joueur joueur) {
        joueur.piocher(2);
    }
}
