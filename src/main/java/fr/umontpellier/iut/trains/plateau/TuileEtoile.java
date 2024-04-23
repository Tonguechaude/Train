package fr.umontpellier.iut.trains.plateau;

import fr.umontpellier.iut.trains.Joueur;

/**
 * Classe représentant une tuile étoile (lieu éloigné)
 */
public class TuileEtoile extends Tuile {
    /**
     * Valeur du lieu éloigné (valeur indiquée sur le plateau)
     */
    private int valeur;

    public TuileEtoile(int valeur) {
        super();
        this.valeur = valeur;
    }

    @Override
    public int surcoutPoseDeRail(Joueur joueur) {

        int surcout = super.surcoutPoseDeRail(joueur);

        //VoieSouterraine
        if(surcout == -1)
        {
            return 0;
        }

        if (!joueur.getListReductions().contains(TypeTerrain.ETOILE)) {
            surcout += valeur;
        }

        return surcout;
    }
    @Override
    public boolean tuileInitialisation() {
        return false;
    }

    @Override
    public int getPointVictoire(Joueur joueur) {
        if(hasRail(joueur))
        {
            return valeur;
        }
        return 0;
    }
}
