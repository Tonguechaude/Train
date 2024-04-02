package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;


public class Appartement extends CarteJaune{

    public Appartement() {
        super("Appartement", 3);
    }

    @Override
    public void jouer(Joueur joueur)
    {
        joueur.addCarteRecue(new Ferraille());
    }
}
