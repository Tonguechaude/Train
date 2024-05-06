package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class TrainDeMarchandises extends CarteDoubleType {
    public TrainDeMarchandises() {
        super("Train de marchandises",4,1, "Train", "Action");
    }

    @Override
    public void jouer(Joueur joueur)
    {

        List<String> choixPossibles = new ArrayList<>();

        for(String nomCarte : joueur.getNomsCartes(joueur.getMain()))
        {
            if(nomCarte.equals("Ferraille"))
            {
                choixPossibles.add(nomCarte);
            }
        }

        if(!choixPossibles.isEmpty())
        {
            String instructions = "Veuillez choisir une carte FERRAILLE à retirer de votre main, ou PASSEZ pour terminer l'effet de la carte.";
            String choix = joueur.choisir(instructions,choixPossibles,null,true);
            while(!choix.isEmpty())
            {
                choixPossibles.remove(choix);
                joueur.removeFerraille(1);
                joueur.ajouterArgent(1);
                choix = joueur.choisir(instructions,choixPossibles,null,true);
            }
        }

    }
}
