package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CentreDeRenseignements extends CarteRouge {
    public CentreDeRenseignements() {
        super("Centre de renseignements", 4, 1);
    }

    public void joueur (Joueur joueur)
    {

       /* if (joueur.getPioche().size() < 4)
        {
            List<Carte> carteRetournees2 = new ArrayList<>();
            for (Carte c : joueur.getPioche())
            {
                carteRetournees2.add(c);
            }
        }*/


        List<Carte> cartesRetournees = joueur.piocher(4);
        List<Bouton> listeBouton = new ArrayList<>();

        joueur.getJeu().log("Cartes Devoilées : ");
        for (Carte carte : cartesRetournees)
        {
            joueur.getJeu().log(carte.getNom());
            listeBouton.add(new Bouton(carte.getNom()));
        }

        // Demander au joueur de choisir une carte à mettre en main
        String choixMain = joueur.choisir("Choisissez une carte à mettre en main ou passez :", null, listeBouton, true);

        if(!choixMain.equals(""))
        {
            Carte carteMain = null;
            for (Carte carte : cartesRetournees)
            {
                if (carte.getNom().equals(choixMain))
                {
                    carteMain = carte;
                }
            }
            if (carteMain != null)
            {
                joueur.ajouterCarteMain(carteMain);
                cartesRetournees.remove(carteMain);
            }
        }

        while (!cartesRetournees.isEmpty()) {
            String choixReplacer = joueur.choisir("Choisissez une carte à replacer sur la pioche :", null, listeBouton, false);
            Carte carteReplacer = null;
            for (Carte carte : cartesRetournees) {
                if (carte.getNom().equals(choixReplacer)) {
                    carteReplacer = carte;
                }
            }
            joueur.getPioche().add(joueur.getPioche().size() - 1, carteReplacer);
            cartesRetournees.remove(carteReplacer);
        }




    }
}


