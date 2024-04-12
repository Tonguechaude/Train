package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class AtelierDeMaintenance extends CarteRouge {
    public AtelierDeMaintenance() {
        super("Atelier de maintenance", 5, 0);
    }

    public void jouer(Joueur joueur)
    {
        /*
        Collection<String> cartesTrain = new ArrayList<>();

        // Parcourir la main du joueur pour récupérer les cartes de type train
        for (Carte carte : joueur.getMain()) {
            if (carte.getType().equals("Train")) {
                cartesTrain.add(carte.getNom());
            }
        }

        // Demander au joueur de choisir une carte Train à dévoiler
        String carteDevoilee = joueur.choisir(
                "Choisissez une carte Train à dévoiler :",
                cartesTrain,
                null,
                false);

        // Retirer la carte dévoilée de la main du joueur
        Carte carteRetiree = null;
        for (Carte carte : joueur.getMain()) {
            if (carte.getNom().equals(carteDevoilee)) {
                carteRetiree = carte;
                break;
            }
        }
        if (carteRetiree != null) {
            joueur.retirerCarteMain(carteRetiree);
        }

        // Vérifier s'il reste une carte identique dans la réserve
        Carte nouvelleCarte = joueur.getJeu().prendreDansLaReserve(carteDevoilee);
        if (nouvelleCarte != null) {
            // Ajouter la nouvelle carte à la main du joueur
            joueur.ajouterCarteMain(nouvelleCarte);
        }
        */


        ArrayList<String> choix = new ArrayList<>();
        for (Carte c : joueur.getMain()) // récupération de toutes les cartes de type Trains en jeu
        {
            if (c.getType().equals("Train") && !choix.contains(c.getNom())) //ajout des cartes trains dans la liste de choix
            {
                choix.add(c.getNom());
            }
        }
        if (choix.isEmpty()) //si aucune carte train en jeu, la carte ne fait rien
        {
            joueur.getJeu().log("aucune cartes <TRAIN> en jeu, vous recevez 1 d'argent (carte Parc d'attractions)");
        }
        else
        {
            String instructions = "Entrez le nom ou cliquez sur une carte en jeu de type <TRAIN> que vous voulez devoilez";
            String nomCarteChoisie = joueur.choisir(instructions, choix, null, false);

            joueur.getJeu().log(String.format("Le joueur %s devoile la carte %s !", joueur.getNom(), nomCarteChoisie));

            Carte carteReserve = joueur.getJeu().prendreDansLaReserve(nomCarteChoisie);
            if (carteReserve != null)
            {
                joueur.ajouterCarteReçue(carteReserve);
                joueur.getJeu().log(String.format("%s reçoit une carte %s", joueur.getNom(), carteReserve.getNom()));
            }
            else
            {
                joueur.getJeu().log("Aucune carte identique en réserve.");
            }
        }
        //joueur.getJeu().log(String.format("%s ne peut pas dévoiler de carte Train.", joueur.getNom()));
    }
}

