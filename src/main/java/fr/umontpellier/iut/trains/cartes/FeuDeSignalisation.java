package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.Arrays;
import java.util.List;

public class FeuDeSignalisation extends CarteRouge {
    public FeuDeSignalisation() {
        super("Feu de signalisation", 2);
    }
    public void jouer (Joueur joueur)
    {
        joueur.ajouterCarteMain(joueur.piocher());

        if(joueur.getPioche().isEmpty() && joueur.getDefausse().isEmpty())
        {
            Carte piocher = joueur.piocher();
            List<Bouton> choix = Arrays.asList(
                    new Bouton("Oui !","oui"),
                    new Bouton("Non !","non"));
            joueur.log(piocher.getNom());
            joueur.choisir("Voulez vous la defaussez ?", null, choix,false);
            if (choix.equals("oui"))
            {
                joueur.getPioche().retirer(piocher.getNom());
                joueur.getDefausse().add(piocher);
                joueur.getJeu().getCartesEcartees().add(piocher);
            }
            else
            {
                joueur.getPioche().add(0,piocher);
            }

        }
    }
}
