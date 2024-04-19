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

    public void joueur (Joueur j)
    {
        List<String> choix = new ArrayList<>();
        int compteurDefausse = 0;

        for (Carte c : j.getMain())
        {
            choix.add(c.getNom());
            choix.add("");

            String input = j.choisir("Voulez-vous faire ceci ?", choix, null, false);

            if (input.equals(c.getNom()))
            {
                j.defausserCarte(c);
                choix.clear();
                compteurDefausse ++;
            }
            else if (input.equals(""))
            {
                j.getMain().addAll(j.piocher(compteurDefausse));
                choix.clear();
                return;
            }

        }

    }


}
