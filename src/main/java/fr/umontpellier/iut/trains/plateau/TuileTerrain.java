package fr.umontpellier.iut.trains.plateau;

import fr.umontpellier.iut.trains.Joueur;

/**
 * Classe représentant une tuile plaine, fleuve ou montagne.
 */
public class TuileTerrain extends Tuile {

    public TuileTerrain(TypeTerrain type) {
        super();
        typeTerrain = type;
    }
    @Override
    public int surcoutPoseDeRail(Joueur joueur) {

        int surcout = super.surcoutPoseDeRail(joueur);

        if(surcout == -1 && typeTerrain.valeurTerrain() != -1)
        {
            return 0;
        }

        if(!joueur.getListReductions().contains(typeTerrain))
        {
            surcout += typeTerrain.valeurTerrain();
        }

        return surcout;
    }
}
