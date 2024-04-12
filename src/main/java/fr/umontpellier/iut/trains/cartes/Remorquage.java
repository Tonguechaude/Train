package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Remorquage extends CarteRouge {
    public Remorquage() {
        super("Remorquage",3);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> listChoix = new ArrayList<>();
        for(Carte carteTrain : joueur.getDefausse())
        {
            if (carteTrain.getType().equals("Train") && !listChoix.contains(carteTrain.getNom()))
            {
                listChoix.add(carteTrain.getNom());
            }
        }
        if(!listChoix.isEmpty())
        {
            String instructions = "entrez le nom ou cliquez sur une carte de type Train présente dans votre défausse pour la récupérer dans votre main";
            String choix = joueur.choisir(instructions, listChoix,null,true);

            if(!choix.equals(""))
            {
                joueur.ajouterCarteMain(joueur.getDefausse().retirer(choix));
            }
        }

    }
}
