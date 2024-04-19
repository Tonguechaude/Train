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
        TuileVille tuileChoisie = new TuileVille(0);
        if(tuileChoisie.peutPoserGare(joueur) || joueur.getJeu().getNbJetonsGare() < 1)
        {

        }




    }
}
