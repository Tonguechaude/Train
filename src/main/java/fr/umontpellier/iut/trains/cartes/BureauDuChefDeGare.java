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

        // Afficher les cartes d'action dans la main du joueur et leur position
        for (Carte c : joueur.getMain()) {
            if (c.getType().equals("Action")) {
                joueur.getJeu().log(String.format("%s", c.getNom()));
                cartesAction.add(c);
                nomsCartesAction.add(c.getNom());
            }
        }

        // Demander au joueur de choisir une carte d'action
        String choixCarte = joueur.choisir("Choisissez une carte d'action que vous avez en main :", nomsCartesAction, null, false);

        Carte carteChoisie = cartesAction.getCarte(choixCarte);
        // Vérifier si le choix du joueur est valide
        joueur.getJeu().log(String.format("Vous avez choisi la carte d'action : %s", carteChoisie.getNom()));

        // Copier l'effet de la carte choisie
        carteChoisie.jouer(joueur); // Exécuter l'effet de la carte choisie


        //joueur.getJeu().log("Choix invalide. La carte d'action choisie n'existe pas.");

    }
}

