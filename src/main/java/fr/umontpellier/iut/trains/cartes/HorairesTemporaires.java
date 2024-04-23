package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class HorairesTemporaires extends CarteRouge {
    public HorairesTemporaires() {
        super("Horaires temporaires", 5,0);
    }

    public void jouer (Joueur joueur)
    {
        //a revoir, piocher retire déjà la carte
        int compteurDeTrain = 0;

        while (compteurDeTrain < 2)
        {
            Carte piocher = joueur.piocher();
            if (piocher instanceof CarteBleu)
            {
                compteurDeTrain++;
                joueur.ajouterCarteMain(piocher);
                joueur.getPioche().retirer(piocher.getNom());
            }
            else
            {
                joueur.defausserCarte(piocher);
                joueur.getPioche().retirer(piocher.getNom());
            }
        }
    }
}
