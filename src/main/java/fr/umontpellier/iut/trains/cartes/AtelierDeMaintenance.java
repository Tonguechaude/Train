package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;


public class AtelierDeMaintenance extends CarteRouge {
    public AtelierDeMaintenance() {
        super("Atelier de maintenance", 5, 0);
    }

    public void jouer(Joueur joueur)
    {
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
            joueur.getJeu().log("aucune carte TRAIN en jeu, vous recevez 1 d'argent");
        }
        else
        {
            String instructions = "Entrez le nom ou cliquez sur une carte en jeu de type TRAIN que vous voulez dévoiler";
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
    }
}

