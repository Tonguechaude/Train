package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Echangeur extends CarteRouge {
    public Echangeur() {
        super("Échangeur", 3,1);
    }

    /*public void jouer (Joueur joueur)
    {
        // Récupérer les cartes TRAIN du joueur
        List<Carte> cartesTrain = new ArrayList<>();
        for (Carte carte : joueur.getZoneDeJeu()) {
            if (carte instanceof CarteBleu) { // Carte bleu c'est le type Train
                cartesTrain.add(carte);
            }
        }

        // Si le joueur n'a pas de cartes TRAIN en jeu, il passe automatiquement
        if (cartesTrain.isEmpty()) {
            joueur.getJeu().log("Le joueur n'a pas de cartes TRAIN en jeu. Il passe.");
            return;
        }

        // Afficher les cartes TRAIN disponibles pour l'échange
        System.out.println("Cartes TRAIN disponibles pour l'échange :");
        for (int i = 0; i < cartesTrain.size(); i++) {
            System.out.println((i + 1) + ". " + cartesTrain.get(i).getNom());
        }

        List<String> choixPossible = new ArrayList<>();
        for



        // Demander au joueur de choisir une carte à échanger
        String choix = joueur.choisir("Choisissez le numéro de la carte TRAIN à remettre sur le dessus de votre deck : ", true, null, true);

        // Si le joueur choisit de passer ou si le choix est invalide, ne rien faire
        if (choix.equals("")) {
            joueur.getJeu().log("Le joueur a choisi de passer");
            return;
        }

        // Remettre la carte choisie sur le dessus du deck du joueur
        Carte carteRetiree = cartesTrain.get(choix - 1);
        joueur.remplacerCarteSurDeck(carteRetiree);
        System.out.println("La carte " + carteRetiree.getNom() + " a été remise sur le dessus du deck.");
    }*/

}
