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
        typeTerrain = TypeTerrain.VILLE;
    }

    @Override
    public int surcoutPoseDeRail(Joueur joueur) {

        //VoieSouterraine
        int surcout = super.surcoutPoseDeRail(joueur);

        if (surcout == -1 || joueur.getListReductions().contains(TypeTerrain.VILLE))
        {
            return 0;
        }
        else
        {
            surcout += 1;
            surcout += this.nbGaresPosees;
        }
        return surcout;
    }



    public boolean peutPoserGare (Joueur joueur)
    {
        if(nbGaresPosees >= nbGaresMax)
        {
            return false;
        }
        else if (joueur.getJeu().getNbJetonsGare() < 1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void ajouterGare ()
    {
        nbGaresPosees++;
    }

    @Override
    public int getNbGares() {
        return nbGaresPosees;
    }

    public int getNbGaresMax() {
        return nbGaresMax;
    }

    @Override
    public int getPointVictoire(Joueur joueur) {
        if(hasRail(joueur)) {
            return switch (nbGaresPosees) {
                case 1 -> 2;
                case 2 -> 4;
                case 3 -> 8;
                default -> 0;
            };
        }
        return 0;
    }
}
