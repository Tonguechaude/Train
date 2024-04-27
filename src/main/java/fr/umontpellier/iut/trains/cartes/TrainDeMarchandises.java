package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrainDeMarchandises extends CarteDoubleType {
    public TrainDeMarchandises() {
        super("Train de marchandises",4,1, "Train", "Action");
    }
    //A TESTER : si le joueur passe directement

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = new ArrayList<>();
        int nbFerrailleEnMain = joueur.getMain().count("Ferraille");
        for(int i = 1 ; i <= nbFerrailleEnMain ; i++) {
            choixPossibles.add("Ferraille");
        }
        String isntructions = "Veuillez choisir la carte <FERRAILLE> à retirer de votre main.";
        String choix = joueur.choisir(isntructions,choixPossibles,null,true);
        while(choixPossibles.contains(choix) || !choix.isEmpty()) {
            choixPossibles.remove(choix);
            joueur.removeFerraille(1);
            joueur.addArgent(1);
            choix = joueur.choisir(isntructions,choixPossibles,null,true);
        }
    }
}
