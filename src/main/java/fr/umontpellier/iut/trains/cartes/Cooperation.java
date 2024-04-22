package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.TypeTerrain;

public class Cooperation extends CarteVerte {
    public Cooperation() {
        super("Coopération", 5, 0);
    }

    public void jouer (Joueur joueur)
    {
        joueur.addPointDeRails(1); // Incrémente le nombre de pointsRails du joueur de 1
        joueur.addFerraille(1);
        joueur.getListReductions().add(TypeTerrain.JOUEUR);



    }


}
