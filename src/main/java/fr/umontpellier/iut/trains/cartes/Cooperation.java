package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.TypeTerrain;

public class Cooperation extends CarteVerte {

    private boolean estActif = false;
    public Cooperation() {
        super("Coopération", 5, 0);
    }

    public void jouer (Joueur joueur)
    {
        super.jouer(joueur);
        joueur.getListReductions().add(TypeTerrain.JOUEUR);
        estActif = true;
    }

    public boolean getEstActif()
    {
        return estActif;
    }
}
