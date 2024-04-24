package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.TypeTerrain;

public class VoieSouterraine extends CarteVerte {
    public VoieSouterraine() {
        super("Voie souterraine",7);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.getListReductions().add(TypeTerrain.TOUTYPE);
    }
}
