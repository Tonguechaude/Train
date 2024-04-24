package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsineDeWagons extends CarteRouge {
    public UsineDeWagons() {
        super("Usine de wagons",5);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = new ArrayList<>();

        //ajout des cartes trains en main dans les choixPossibles
        for(Carte CarteTrain : joueur.getMain())
        {
            if(CarteTrain.getType().equals("Train"))
            {
                choixPossibles.add(CarteTrain.getNom());
            }
        }
        //si aucune carte Train en main, impossible d'effectuer l'action de la carte
        if(!choixPossibles.isEmpty())
        {
            //on ajoute la carte retirée de la main dans les cartes écartées du jeu
            String instructions = "Veuillez choisir une carte à écarter.";
            // ne peut pas passer sinon il pourrait gagner une carte sans en retirer une
            String choix = joueur.choisir(instructions,choixPossibles,null,false);
            int valMax = joueur.getMain().getCarte(choix).getPrix() + 3;

            joueur.getJeu().getCartesEcartees().add(joueur.getMain().retirer(choix));
            choixPossibles.clear();

            for(ListeDeCartes listCheck : joueur.getJeu().getReserve().values())
            {
                if(!listCheck.isEmpty())
                {
                    Carte check = listCheck.get(0);
                    if(check.getType().equals("Train") && check.getPrix() <= valMax)
                    {
                        choixPossibles.add("ACHAT:" + check.getNom());
                    }
                }
            }
            instructions = "Veuillez choisir une carte TRAIN de la réserve à ajouter à votre main.";
            choix = joueur.choisir(instructions,choixPossibles,null,true);
            if(!choix.isEmpty())
            {
                choix = choix.split(":")[1];
                Carte carteChoisie = joueur.getJeu().getReserve().get(choix).get(0);
                joueur.ajouterCarteMain(carteChoisie);
                joueur.getJeu().getReserve().get(choix).remove(carteChoisie);
            } else
            {
                joueur.log("Aucune carte n'a été ajoutée à votre main.");
            }
        } else
        {
            joueur.log("Aucune carte TRAIN disponible dans votre main, l'action ne peut être réalisée.");
        }
    }
}
