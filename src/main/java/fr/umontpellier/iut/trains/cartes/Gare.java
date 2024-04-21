package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.Tuile;
import fr.umontpellier.iut.trains.plateau.TuileVille;

import java.util.ArrayList;
import java.util.List;

public class Gare extends CarteViolette {
    public Gare() {
        super("Gare", 3, 0);
    }

    public void jouer (Joueur joueur)
    {

        if(joueur.getJeu().getNbJetonsGare() < 1)
        {
            joueur.addFerraille(1);
            return;
        }

        List<Bouton> boutons = new ArrayList<>();
        List<Tuile> tuiles = joueur.getJeu().getTuiles();
        int index = 0;
        for ( Tuile tuile : tuiles)
        {
            if(tuile instanceof TuileVille)
            {
                boutons.add(new Bouton("TUILE:"+ index));
            }
        }

        String input = joueur.choisir("Choisissez une tuile : ", null, boutons, false);
        int indexTuile = Integer.parseInt(input.replaceAll("[^0-9]", ""));
        // la ligne dessus est assez complexe frero, elle m'a demandé de la recherche en gros replaceAll va remplacer
        // tous les charactere par le deuxieme argument, en l'occurence du vide ("") et le [^0-9] tout sauf ce qui est un chiffre

        TuileVille tuileChoisie = (TuileVille) joueur.getJeu().getTuile(indexTuile);

        if(tuileChoisie.peutPoserGare(joueur))
        {
            joueur.getJeu().addNbJetonGare(-1);
            joueur.addFerraille(1);
        }
    }
}
