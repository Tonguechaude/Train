package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.Arrays;
import java.util.List;

public class PersonnelDeGare extends CarteRouge {
    public PersonnelDeGare() {
        super("Personnel de gare",2);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<Bouton> boutons = Arrays.asList(
                new Bouton("piocher","piocher"),
                new Bouton("argent","argent"),
                new Bouton("ferraille","ferraille"));

        String instructions = "veuillez choisir entre piocher 1 carte, gagner 1 d'argent ou remettre 1 de ferraille dans la pile";
        String choix = joueur.choisir(instructions,null,boutons,true);

        switch (choix)
        {
            case "piocher":
                joueur.getMain().add(joueur.piocher());
                break;
            case "argent":
                joueur.addArgent(1);
                break;
            case "ferraille":
                joueur.removeFerraille(1);
                break;
        }
    }
}
