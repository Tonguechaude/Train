package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Echangeur extends CarteRouge {
    public Echangeur() {
        super("Échangeur", 3,1);
    }

   public void jouer (Joueur joueur)
    {
        ArrayList<String> choix = new ArrayList<>();

        for (Carte c : joueur.getCartesEnJeu()) // récupération de toutes les cartes de type Trains en jeu
        {
            if (c.getType().equals("Train") && !choix.contains(c.getNom())) //ajout des cartes trains dans la liste de choix
            {
                choix.add(c.getNom());
            }
        }
        String nomCarteChoisie = joueur.choisir("Choisissez une carte Train ", choix, null, true);
        joueur.getPioche().add(0, joueur.getCartesEnJeu().retirer(nomCarteChoisie));

    }

}
