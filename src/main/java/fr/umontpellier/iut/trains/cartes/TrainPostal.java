package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainPostal extends CarteDoubleType {
    public TrainPostal() {
        super("Train postal",4,1, "Train", "Action");
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = joueur.getNomsCartes(joueur.getMain());
        String instructions = "Choisissez une carte à défausser de votre main ou passez pour terminer l'effet de la carte.";
        String choix = joueur.choisir(instructions,choixPossibles,null,true);
        while(!choix.isEmpty())
        {
            joueur.getDefausse().add(joueur.getMain().retirer(choix));
            joueur.addArgent(1);
            choix = joueur.choisir(instructions,choixPossibles,null,true);
        }
    }
}
