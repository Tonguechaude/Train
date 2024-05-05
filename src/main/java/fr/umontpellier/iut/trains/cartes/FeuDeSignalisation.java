package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.Arrays;
import java.util.List;

public class FeuDeSignalisation extends CarteRouge
{
    public FeuDeSignalisation() {
        super("Feu de signalisation", 2);
    }

    public void jouer (Joueur joueur)
    {
        joueur.ajouterCarteMain(joueur.piocher());

        Carte piocher = joueur.piocher();
        if(piocher != null)
        {
            List<Bouton> choix = Arrays.asList(
                    new Bouton("Oui !", "oui"),
                    new Bouton("Non !", "non"));
            joueur.log(piocher.getNom());
            String input = joueur.choisir("Voulez vous la défausser ?", null, choix, false);
            if (input.equals("oui"))
            {
                joueur.getDefausse().add(piocher);
            }
            else
            {
                joueur.remettreAuDessusPioche(piocher);
            }
        }
        else
        {
            joueur.log("aucune carte disponibles");
        }
    }
}
