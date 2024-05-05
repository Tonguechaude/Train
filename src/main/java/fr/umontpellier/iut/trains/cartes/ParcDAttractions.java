package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;

public class ParcDAttractions extends CarteRouge {
    public ParcDAttractions() {
        super("Parc d'attractions",4,1);
    }
    //Recevez X¥. X est égal à la valeur d'une de vos cartes TRAIN en jeu.
    @Override
    public void jouer(Joueur joueur) {
        ArrayList<String> choix = new ArrayList<>();
        for (Carte c : joueur.getCartesEnJeu()) // récupération de toutes les cartes de type Trains en jeu
        {
            if (c.getType().equals("Train") && !choix.contains(c.getNom())) //ajout des cartes trains dans la liste de choix
            {
                choix.add(c.getNom());
            }
        }
        if (choix.isEmpty()) //si aucune carte train en jeu, la carte ne fait rien
        {
            joueur.getJeu().log("Aucune carte de type TRAIN en jeu, vous recevez 1 d'argent (carte Parc d'attractions).");
        } else
        {
            String instructions = "Entrez le nom ou cliquez sur une carte en jeu de type <TRAIN> dont vous voulez recevoir sa valeur.";
            String nomCarteChoisie = joueur.choisir(instructions, choix, null, true);

            joueur.ajouterArgent(joueur.getCartesEnJeu().getCarte(nomCarteChoisie).getValeur());
        }
    }
}
