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
    public int getValeur(){return valeur;}

    @Override
    public int surcoutPoseDeRail(Joueur joueur) {

        int surcout = super.surcoutPoseDeRail(joueur);

        //VoieSouterraine
        if(surcout == -2)
        {
            return 0;
        }

        if (!joueur.getListReductions().contains(TypeTerrain.ETOILE)) {
            surcout += valeur;
        }

        return surcout;
    }
}
