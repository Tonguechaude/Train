package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class CarteVerte extends Carte
{

    private final String type;

    @Override
    public String getType() {
        return type;
    }

    public CarteVerte(String nom, int prix) {
        super(nom, prix);
        this.type = "Rail";

    }

    public CarteVerte(String nom, int prix, int valeur) {
        super(nom, prix, valeur);
        this.type = "Rail";
    }

    @Override
    public void jouer(Joueur joueur) {
        int compteur = 0;
        for(String nomCarte : joueur.getListCarteSpeciales())
        {
            if(nomCarte.equals("Ferronnerie"))
            {
                compteur++;
            }
        }
        joueur.ajouterArgent(compteur * 2);
        joueur.addFerraille(1);
        joueur.addPointDeRails(1);
    }
}
