package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;


public class BureauDuChefDeGare extends CarteRouge {
    public BureauDuChefDeGare() {
        super("Bureau du chef de gare", 4,0);
    }

    @Override
    public void jouer(Joueur joueur)
    {

        List<String> nomsCartesAction = new ArrayList<>();
        for (Carte c : joueur.getMain())
        {
            if ((c.getType().equals("Action") || c.getSecondType().equals("Action")) && (c != this))
            {
                nomsCartesAction.add(c.getNom());
            }
        }

        if(!nomsCartesAction.isEmpty())
        {
            String choixCarte;
            {
                choixCarte = joueur.choisir("Choisissez une carte de type Action que vous avez en main :", nomsCartesAction, null, false);

            }

            if(!choixCarte.isEmpty())
            {
                Carte carteChoisie = joueur.getMain().getCarte(choixCarte);
                carteChoisie.jouer(joueur); // Exécuter l'effet de la carte choisie


                //gérer horaires estivaux
                //si le choix est horaires estivaux et que la carte à été écartée, il faut remettre les cartes au bon endroit
                if(!joueur.getJeu().getCartesEcartees().isEmpty() && choixCarte.equals("Horaires estivaux") &&
                        joueur.getJeu().getCartesEcartees().get(0).getNom().equals("Horaires estivaux"))
                {
                    // remettre horaires estivaux dans la main du joueur en la retirant des cartes écartées
                    joueur.getMain().add(joueur.getJeu().getCartesEcartees().remove(0));
                    // mettre le chef de gare dans les cartes écartées
                    joueur.getJeu().getCartesEcartees().add(joueur.getCartesEnJeu().retirer("Bureau du chef de gare"));
                }
            }
        }
        else
        {
            joueur.log("Vous ne possédez pas de carte de type Action dans votre main");
        }

    }
}

