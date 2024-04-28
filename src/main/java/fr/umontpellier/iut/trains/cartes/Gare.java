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

        joueur.addFerraille(1);

        if(joueur.getJeu().getNbJetonsGare() >= 1)
        {
            boolean tuileChoisieValide = false;
            List<String> choix = new ArrayList<>();
            List<Tuile> tuiles = joueur.getJeu().getTuiles();
            int index = 0;
            for ( Tuile tuile : tuiles)
            {
                if(tuile instanceof TuileVille)
                {
                    choix.add("TUILE:" + index);
                }
                index ++;
            }
            while(!tuileChoisieValide) {
                String input = joueur.choisir("Choisissez une tuile (Ville) où poser votre gare: ", choix, null, false);
                int indexTuile = Integer.parseInt(input.split(":")[1]);
                //int indexTuile = Integer.parseInt(input.replaceAll("[^0-9]", ""));
                // la ligne dessus est assez complexe frero, elle m'a demandé de la recherche en gros replaceAll va remplacer
                // tous les charactere par le deuxieme argument, en l'occurence du vide ("") et le [^0-9] tout sauf ce qui est un chiffre

                TuileVille tuileChoisie = (TuileVille) joueur.getJeu().getTuile(indexTuile);

                if (tuileChoisie.peutPoserGare(joueur)) {
                    joueur.getJeu().enleverJetonGare();
                    tuileChoisie.ajouterGare();
                    tuileChoisieValide = true;
                } else {
                    joueur.log("Impossible de poser une gare ici.");
                }
            }
        }
    }
}
