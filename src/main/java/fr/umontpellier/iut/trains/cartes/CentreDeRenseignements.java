package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class CentreDeRenseignements extends CarteRouge {
    public CentreDeRenseignements() {
        super("Centre de renseignements", 4, 1);
    }

    public void joueur (Joueur joueur)
    {
        // Afficher les 4 premières cartes de la pioche
        List<Carte> cartesRetournees = joueur.piocher(4);
        joueur.getJeu().prompt("Cartes retournées :", null, false);
        for (Carte carte : cartesRetournees) {
            joueur.getJeu().log(carte.getNom());
        }

        // Demander au joueur de choisir une carte à mettre en main
        String choixMain = joueur.choisir("Choisissez une carte à mettre en main ou passez :", joueur.getNomsCartes(cartesRetournees), null, true);
        Carte carteMain = null;
        for (Carte carte : cartesRetournees) {
            if (carte.getNom().equals(choixMain)) {
                carteMain = carte;
            }
        }
        if (carteMain != null) {
            joueur.ajouterCarteMain(carteMain);
            cartesRetournees.remove(carteMain);
        }

        // Demander au joueur de choisir les cartes à replacer sur la pioche
        while (!cartesRetournees.isEmpty()) {
            String choixReplacer = joueur.choisir("Choisissez une carte à replacer sur la pioche :", joueur.getNomsCartes(cartesRetournees), null, false);
            Carte carteReplacer = null;
            for (Carte carte : cartesRetournees) {
                if (carte.getNom().equals(choixReplacer)) {
                    carteReplacer = carte;
                }
            }
            joueur.getPioche().add(joueur.getPioche().toArray().length, carteReplacer);
            cartesRetournees.remove(carteReplacer);
        }
    }
}

