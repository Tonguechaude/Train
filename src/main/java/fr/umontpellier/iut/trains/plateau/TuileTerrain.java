package fr.umontpellier.iut.trains.plateau;

import fr.umontpellier.iut.trains.Joueur;

/**
 * Classe représentant une tuile plaine, fleuve ou montagne.
 */
public class TuileTerrain extends Tuile {
    /**
     * Type de terrain de la tuile ({@code PLAINE}, {@code FLEUVE} ou {@code MONTAGNE})
     */
    private TypeTerrain type;

    public TuileTerrain(TypeTerrain type) {
        super();
        this.type = type;
    }
    @Override
    public int surcoutPoseDeRail(Joueur joueur) {

        int surcout = super.surcoutPoseDeRail(joueur);

        if(surcout == -1 && type.valeurTerrain() != -1)
        {
            return 0;
        }

        if(!joueur.getListReductions().contains(type))
        {
            surcout += type.valeurTerrain();
        }

        return surcout;
    }
}
