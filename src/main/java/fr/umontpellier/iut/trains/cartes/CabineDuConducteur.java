package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CabineDuConducteur extends CarteRouge {
    public CabineDuConducteur() {
        super("Cabine du conducteur", 2, 0);
    }

    public void joueur (Joueur j)
    {

        int compteur = 0;

        for ( Carte c : j.getMain())
        {
            String input = j.choisir("Voulez-vous faire ceci ?", j.getNomMain(), null, true);
            if (input.equals(c.getNom()))
            {
                j.defausserCarte(c);
                j.ajouterCarteMain(j.piocher());
            }
            else
            {
                return;
            }
        }


        /*
        boolean defausser = false;
        List<String> listeChoix = Arrays.asList("defausse", "passer");
        ListeDeCartes liste = new ListeDeCartes();

        while (!defausser)
        {
            String choix = j.choisir("Voulez-vous défausser une carte de votre main ? (defausse ou passer)", listeChoix, null, true);

            if(choix.equals("defausse"))
            {
                if(!j.getMain().isEmpty())
                {
                    String carteADefausser = j.choisir("Choisissez une carte à défausser :", j.getNomMain(),null, true);
                    j.ajouterCarteDefausse((j.getMain().getCarte(carteADefausser)));
                    j.retirerCarteMain(j.getMain().getCarte(carteADefausser));
                    j.piocher();
                }
                else
                {
                    j.getJeu().log("Votre main est vide, vous ne pouvez pas défausser de carte.");
                }
            }
            else if (choix.equals("passer"))
            {
                defausser = true;
            }
        }*/

    }

}
