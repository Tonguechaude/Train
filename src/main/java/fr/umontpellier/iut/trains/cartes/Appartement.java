package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;


public class Appartement extends Carte {
    public Appartement() {
        super("Appartement", 3, TypeCarte.POINTDEVICTOIRE);
    }

    @Override
    public void jouer(Joueur joueur)
    {
        if(joueur.getArgent() >= 3)
        {
            joueur.setArgent(joueur.getArgent() - 3);
            joueur.addCarteRecue(new Ferraille());
        }
        else
        {
            joueur.log("Bous n'avez pas assez d'argent !");
        }
    }
}
