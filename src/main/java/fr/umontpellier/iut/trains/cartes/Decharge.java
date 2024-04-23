package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;

public class Decharge extends CarteRouge {
    public Decharge() {
        super("Décharge", 2, 0);
    }

    public void jouer (Joueur joueur)
    {
        joueur.removeFerraille(-1);

        /*ListeDeCartes cartesFerraille = new ListeDeCartes();
        for (Carte c : joueur.getMain())
        {
            //if (c.getNom().equals("Ferraille"))
            if (c instanceof Ferraille)
            {
                cartesFerraille.add(c);
            }

        }
        for (Carte carte : cartesFerraille)
        {
            joueur.retirerCarteMain(carte);
            joueur.getJeu().getReserve().get("Ferraille").add(carte);
        }*/



    }



}
