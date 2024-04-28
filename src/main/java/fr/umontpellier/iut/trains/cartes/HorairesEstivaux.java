package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.Arrays;
import java.util.List;

public class HorairesEstivaux extends CarteRouge {
    public HorairesEstivaux() {
        super("Horaires estivaux", 3, 0);
    }

    public void jouer (Joueur joueur)
    {
        List<Bouton> choix = Arrays.asList(
                new Bouton("Oui !","oui"),
                new Bouton("Non !","non"));
        String input = joueur.choisir("Voulez vous écarter cette carte ?", null, choix, false);
        if (input.equals("oui"))
        {
            joueur.addArgent(3);
            joueur.getCartesEnJeu().retirer(this.getNom());
            joueur.getJeu().getCartesEcartees().add(0,this);
            joueur.retirerCarteMain(this);
        }
    }
}
