package fr.umontpellier.iut.trains.cartes;

import ch.qos.logback.core.joran.action.NewRuleAction;
import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Depot extends CarteRouge {
    public Depot() {
        super("Dépôt", 3, 1);
    }

    public void joueur (Joueur joueur)
    {

        joueur.getMain().add(joueur.piocher());
        joueur.getMain().add(joueur.piocher());

        /*List<Bouton> listeChoix = new ArrayList<>();
        for (Carte c : joueur.getMain())
        {
            listeChoix.add(new Bouton(c.getNom()));
        }

        for (int i = 0; i < 2; i++)
        {
            String input = joueur.choisir("Qelle carte souhaitez vous defaussez ? ", null, listeChoix, false);
            joueur.defausserCarte(joueur.getMain().getCarte(input));
        }
*/


        /*je l'ai mis, mais la consigne est bizarre
        if (joueur.getMain().size() < 2) {
            joueur.getDefausse().addAll(joueur.getMain());
            joueur.getMain().clear();
            return;
        }*/




    }
}
