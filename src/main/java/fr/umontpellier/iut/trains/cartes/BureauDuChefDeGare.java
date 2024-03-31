package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BureauDuChefDeGare extends Carte {
    public BureauDuChefDeGare() {
        super("Bureau du chef de gare", 4, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur) {

        // Vérifier si le joueur a assez d'argent pour utiliser la carte
        if (joueur.getArgent() >= 3) {
            joueur.setArgent(joueur.getArgent() - 3); // Dépenser 3 d'argent pour utiliser la carte

            List<Carte> cartesAction = new ArrayList<>();
            List<String> nomsCartesAction = new ArrayList<>();

            // Afficher les cartes d'action dans la main du joueur et leur position
            for (int i = 0; i < joueur.getMain().size(); i++) {
                Carte carte = joueur.getMain().get(i);
                if (carte.getType() == TypeCarte.ACTION) {
                    joueur.getJeu().log(String.format("%d. %s", i + 1, carte.getNom()));
                    cartesAction.add(carte);
                    nomsCartesAction.add(carte.getNom());
                }
            }

            // Demander au joueur de choisir une carte d'action
            int choixCarte = Integer.parseInt(joueur.choisir("Choisissez une carte d'action que vous avez en main :",
                    nomsCartesAction, null, false)) - 1;

            // Vérifier si le choix du joueur est valide
            if (choixCarte >= 0 && choixCarte < cartesAction.size()) {
                Carte carteChoisie = cartesAction.get(choixCarte);
                joueur.getJeu().log(String.format("Vous avez choisi la carte d'action : %s", carteChoisie.getNom()));

                // Copier l'effet de la carte choisie
                joueur.addCarteRecue(carteChoisie); // Ajouter la carte choisie aux cartes reçues du joueur
                carteChoisie.jouer(joueur); // Exécuter l'effet de la carte choisie
            } else {
                joueur.getJeu().log("Choix invalide. La carte d'action choisie n'existe pas.");
            }
        } else {
            joueur.getJeu().log("Pas assez d'argent pour utiliser le Bureau du Chef de Gare.");
        }
    }
}

