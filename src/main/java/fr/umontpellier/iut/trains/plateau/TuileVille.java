package fr.umontpellier.iut.trains.plateau;

import fr.umontpellier.iut.trains.Joueur;

/**
 * Classe représentant une tuile ville (où l'on peut poser des gares)
 */
public class TuileVille extends Tuile {
    /**
     * Nombre maximum de gares que l'on peut poser sur la tuile
     */
    private int nbGaresMax;
    /**
     * Nombre de gares posées sur la tuile
     */
    private int nbGaresPosees;

    public TuileVille(int taille) {
        super();
        this.nbGaresMax = taille;
        this.nbGaresPosees = 0;
    }

    @Override
    public int surcoutPoseDeRail(Joueur joueur) {
        int surcout = 1;

        if(!joueur.getListReductions().contains(TypeTerrain.VILLE) && this.nbGaresPosees > 0) {
            surcout += this.nbGaresPosees;
        }
        return surcout;
    }
}
