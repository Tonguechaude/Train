package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;

public class Decharge extends CarteRouge {
    public Decharge() {
        super("Décharge", 2, 0);
    }

    public void jouer (Joueur joueur)
    {
        // Récupère toutes les cartes FERRAILLE de la main du joueur
        ListeDeCartes cartesFerraille = new ListeDeCartes();
        for (Carte c : joueur.getMain())
        {
            if (c.getNom().equals("Feraille"))
            {
                cartesFerraille.add(c);
            }

        }
        // Ajoute toutes les cartes FERRAILLE de la main du joueur à la pile FERRAILLE
        for (Carte carte : cartesFerraille) {
            joueur.getJeu().getReserve().get("Ferraille").add(carte);
            joueur.retirerCarteMain(carte);
        }

    }



}
