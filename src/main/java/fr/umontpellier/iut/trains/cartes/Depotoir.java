package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Depotoir extends CarteRouge {
    public Depotoir() {
        super("Dépotoir", 5, 1);
    }

    public void jouer (Joueur joueur)
    {

        for ( Carte carte : joueur.getCartesRecues())
        {
            if (carte instanceof Ferraille)
            {
                joueur.getCartesRecues().remove(carte);
            }
        }
        /*List<Carte> cartesSansFerraille = new ArrayList<>();
        for (Carte carte : joueur.getCartesRecues()) {
            if (!(carte instanceof Ferraille)) {
                cartesSansFerraille.add(carte);
            }
        }
        joueur.setCartesRecues(cartesSansFerraille);*/
    }
}
