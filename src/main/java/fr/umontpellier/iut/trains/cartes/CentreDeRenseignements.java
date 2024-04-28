package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CentreDeRenseignements extends CarteRouge {
    public CentreDeRenseignements() {
        super("Centre de renseignements", 4, 1);
    }

    public void jouer (Joueur joueur)
    {
        ListeDeCartes cartesRetournees = new ListeDeCartes();
        cartesRetournees.addAll(joueur.piocher(4));
        List<Bouton> listeBouton = new ArrayList<>();

        joueur.getJeu().log("Cartes Devoilées : ");
        for (Carte carte : cartesRetournees)
        {
            joueur.getJeu().log(carte.getNom());
            listeBouton.add(new Bouton(carte.getNom()));
        }

        String choixMain = joueur.choisir("Choisissez une carte à mettre en main ou passez :", null, listeBouton, true);

        if(!choixMain.equals(""))
        {
            listeBouton.remove(new Bouton(choixMain));
            Carte cartechoisie = cartesRetournees.getCarte(choixMain);
            joueur.ajouterCarteMain(cartechoisie);
            cartesRetournees.remove(cartechoisie);

        }

        while (!cartesRetournees.isEmpty()) {
            String choixReplacer = joueur.choisir("Choisissez une carte à replacer sur la pioche :", null, listeBouton, false);

            listeBouton.remove(new Bouton(choixReplacer));
            Carte carteReplacer = cartesRetournees.getCarte(choixReplacer);
            joueur.getPioche().add(0, carteReplacer);
            cartesRetournees.remove(carteReplacer);
        }




    }
}


