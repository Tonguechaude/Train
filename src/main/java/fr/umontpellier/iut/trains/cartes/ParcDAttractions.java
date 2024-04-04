package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;

public class ParcDAttractions extends CarteRouge {
    public ParcDAttractions() {
        super("Parc d'attractions",4,1);
    }
/*
    @Override
    public void jouer(Joueur joueur) {
        ArrayList<String> boutons = new ArrayList<>();
        for(int i = 0 ; i < joueur.getCartesEnJeu().size() ; i++)
        {
            if(joueur.getCartesEnJeu().get(i).getType().equals("Bleu"))
            {
                boutons.add(new Bouton());
            }
        }
    }*/
}
