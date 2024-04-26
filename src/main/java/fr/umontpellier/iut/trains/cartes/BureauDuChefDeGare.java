package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;


public class BureauDuChefDeGare extends CarteRouge {
    public BureauDuChefDeGare() {
        super("Bureau du chef de gare", 4,0);
    }

    @Override
    public void jouer(Joueur joueur) {

        ListeDeCartes cartesAction = new ListeDeCartes();
        List<String> nomsCartesAction = new ArrayList<>();

        for (Carte c : joueur.getMain()) {
            if (c.getType().equals("Action")) {
                cartesAction.add(c);
                nomsCartesAction.add(c.getNom());
            }
        }

        String choixCarte = joueur.choisir("Choisissez une carte d'action que vous avez en main :", nomsCartesAction, null, false);
        Carte carteChoisie = cartesAction.getCarte(choixCarte);
        carteChoisie.jouer(joueur); // Exécuter l'effet de la carte choisie


    }
}

