package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CentreDeControle extends CarteRouge {
    public CentreDeControle() {
        super("Centre de contrôle", 3, 0);
    }

    public void jouer (Joueur joueur)
    {
        joueur.ajouterCarteMain(joueur.piocher());

        List<Bouton> listeBouton = new ArrayList<>();

        for (String str : joueur.getJeu().getListeNomsCartes())
        {
            listeBouton.add(new Bouton(str));

        }

        String input = joueur.choisir("Voulez-vous faire ceci ?", null, listeBouton, true);

        Carte pioche2 = joueur.piocher();

        if (pioche2.getNom().equals(input))
        {
            joueur.ajouterCarteMain(pioche2);
        }
        else
        {
            joueur.remettreAuDessusPioche(pioche2);
        }
    }
}