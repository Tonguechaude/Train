package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class Aiguillage extends CarteRouge {


    private static final int COUT_AIGUILLAGE = 5;
    private static final int NB_CARTES_A_PIOCHER = 2;

    public Aiguillage()
    {
        super("Aiguillage", COUT_AIGUILLAGE,0);
    }

    @Override
    public void jouer(Joueur joueur)
    {
        List<Carte> cartesPiochees = joueur.piocher(NB_CARTES_A_PIOCHER);
        joueur.setMain(cartesPiochees);
    }
}
