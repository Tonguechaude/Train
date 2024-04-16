package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Depot extends CarteRouge {
    public Depot() {
        super("Dépôt", 3, 1);
    }

    public void joueur (Joueur joueur)
    {
        // Piocher automatiquement deux cartes
        List<Carte> cartesPiochees = joueur.piocher(2);

        // Si le joueur a strictement moins de 2 cartes en main, défausser ce qu'il a et terminer l'exécution de la carte
        if (joueur.getMain().size() < 2) {
            joueur.getDefausse().addAll(joueur.getMain());
            joueur.getMain().clear();
            return;
        }

        // Afficher les cartes piochées
        joueur.log("Vous avez pioché : " + cartesPiochees.get(0) + " et " + cartesPiochees.get(1));

        // Demander au joueur de choisir deux cartes à défausser parmi celles qu'il a en main
        List<String> choixPossibles = joueur.getNomMain();
        List<String> cartesDefausser = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            // Demander au joueur de choisir une carte à défausser
            String choix = joueur.choisir("Choisissez une carte à défausser :", choixPossibles, null, false);
            // Ajouter la carte choisie à la liste des cartes à défausser
            cartesDefausser.add(choix);
            // Retirer la carte choisie de la liste des choix possibles
            choixPossibles.remove(choix);
        }

        // Défausser les cartes choisies par le joueur
        for (String nomCarte : cartesDefausser) {
            Carte carteDefausser = joueur.getMain().retirer(nomCarte);
            joueur.defausserCarte(carteDefausser);
        }

    }
}
