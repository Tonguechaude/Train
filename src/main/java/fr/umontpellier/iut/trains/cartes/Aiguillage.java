package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Aiguillage extends Carte {
    public Aiguillage() {
        super("Aiguillage", 5, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {
        if (joueur.getArgent() >= 5)
        {
            joueur.piocher(2);
            joueur.setArgent(joueur.getArgent() - 5);
        }
        else
        {
            joueur.log("Vous n'avez pas assez d'argent !");
        }
    }
}
