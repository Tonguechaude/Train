package fr.umontpellier.iut.trains.plateau;

import fr.umontpellier.iut.trains.Joueur;

/**
 * Classe représentant une tuile de mer (tuile qui ne peut pas être occupée par
 * un rail ou une gare)
 */
public class TuileMer extends Tuile {
    public TuileMer() {
        super();
    }

    @Override
    public int surcoutPoseDeRail(Joueur joueur) {
        joueur.getJeu().log("vous ne pouvez pas poser de rails sur une case <MER>");
        return -1;
    }
    @Override
    public boolean tuileInitialisation() {
        return false;
    }
}
