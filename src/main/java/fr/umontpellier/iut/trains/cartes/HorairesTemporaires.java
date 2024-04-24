package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class HorairesTemporaires extends CarteRouge {
    public HorairesTemporaires() {
        super("Horaires temporaires", 5,0);
    }

    public void jouer (Joueur joueur)
    {
        int compteurDeTrain = 0;

        while (compteurDeTrain < 2)
        {
            Carte piocher = joueur.piocher();
            if (piocher.getType().equals("Train"))
            {
                compteurDeTrain++;
                joueur.ajouterCarteMain(piocher);
            }
            else
            {
                joueur.defausserCarte(piocher);
            }
        }
    }
}
