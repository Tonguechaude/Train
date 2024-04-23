package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Ferronnerie extends CarteRouge {
    public Ferronnerie() {
        super("Ferronnerie", 4, 1);
    }
    //a revoir, il faut gérer l'exception durant tout le tour
    public void jouer (Joueur joueur)
    {
        for (Carte carte : joueur.getCartesEnJeu())
        {
            if (carte instanceof PoseDeRails)
            {
                joueur.addArgent(2);
            }
        }

    }



}
