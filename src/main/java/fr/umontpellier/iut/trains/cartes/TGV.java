package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TGV extends CarteBleu {
    public TGV() {
        super("TGV",2,1);
    }

    @Override
    public void jouer(Joueur joueur) {
        if(joueur.getCartesEnJeu().getCarte("Train omnibus") != null)
        {
            joueur.addArgent(1);
        }
        joueur.getJeu().log(String.format("%s reçoit 1 d'argent", joueur.getNom()));
    }
}
