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

    public void jouer (Joueur joueur)
    {

        joueur.setMain(joueur.piocher(2));

        List<Bouton> listeChoix = new ArrayList<>();
        for (Carte c : joueur.getMain())
        {
            listeChoix.add(new Bouton(c.getNom()));
        }

        int limite = joueur.getMain().size();
        if(limite < 2)
        {
            joueur.defausserToutesLesCartes(joueur.getMain());
        }
        else
        {
            for (int i = 0; i < 2; i++)
            {
                String input = joueur.choisir("Quelle carte souhaitez vous défausser ? ", null, listeChoix, false);
                joueur.defausserCarte(joueur.getMain().getCarte(input));
            }
        }
    }
}
