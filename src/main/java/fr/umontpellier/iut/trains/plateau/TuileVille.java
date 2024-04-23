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
        /*
        //plus possible de poser une gare
        if(nbGaresPosees == nbGaresMax)
        {
            joueur.getJeu().log("vous ne pouvez pas poser de gare sur cette case car le nombre maximal a déjà été atteint");
            return -1;
        } else if (joueur.getJeu().getNbJetonsGare() < 1)
        {
            joueur.getJeu().log("toutes les gares ont déjà été posées");
            return -1;
        }*/

        //VoieSouterraine
        int surcout = super.surcoutPoseDeRail(joueur);
        if (surcout == -1)
        {
            return 0;
        }
        else
        {
            surcout += 1;
        }

        if(!joueur.getListReductions().contains(TypeTerrain.VILLE) && this.nbGaresPosees > 0)
        {
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

    public void addGare ()
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
