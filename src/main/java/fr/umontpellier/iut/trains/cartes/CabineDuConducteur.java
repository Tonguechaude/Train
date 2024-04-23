package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CabineDuConducteur extends CarteRouge {
    public CabineDuConducteur() {
        super("Cabine du conducteur", 2, 0);
    }

    public void jouer (Joueur joueur)
    {
        List<String> choix = joueur.getNomMain();
        int compteurDefausse = 0;

        String input = joueur.choisir("Choisissez les cartes a défausser ou passer.", choix, null, true);

        while (!choix.isEmpty() && !input.isEmpty())
        {
            joueur.defausserCarte(joueur.getMain().getCarte(input));
            choix.remove(input);
            compteurDefausse ++;
            input = joueur.choisir("Choisissez les cartes a défausser ou passer.", choix, null, true);

        }

        joueur.setMain(joueur.piocher(compteurDefausse));
    }
}
