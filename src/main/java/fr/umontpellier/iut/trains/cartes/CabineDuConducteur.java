package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.Arrays;
import java.util.List;

public class CabineDuConducteur extends CarteRouge {
    public CabineDuConducteur() {
        super("Cabine du conducteur", 2);
    }

    public void joueur (Joueur j)
    {
        /*
        List<Bouton> boutons = Arrays.asList(new Bouton("Defausse !", "defausse"), new Bouton("passer !", "passer"));

        for ( Carte c : j.getMain())
        {
            String input = j.choisir("Voulez-vous faire ceci ?", null, boutons, true);
            if (input.equals("defausse"))
            {
                j.piocher();
            }
        }

        */

        boolean defausser = false;
        List<String> choix = Arrays.asList("defausse", "passer");

        while (!defausser)
        {
            String input = j.choisir("Voulez-vous défausser une carte de votre main ? (defausse ou passer)", choix, null, true);
            if(input.equals("defausse"))
            {
                if(!j.getMain().isEmpty())
                {
                    String carteADefausser = j.choisir("Choisissez une carte à défausser :", j.getNomMain(),null, true);
                    j.getDefausse().add(j.getMain().retirer(carteADefausser));
                    j.piocher();
                }
                else
                {
                    j.getJeu().log("Votre main est vide, vous ne pouvez pas défausser de carte.");
                }
            }
            else if (input.equals("passer"))
            {
                defausser = true;
            }
        }

    }

}
