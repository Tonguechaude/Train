package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class Aiguillage extends Carte {


    private static final int COUT_AIGUILLAGE = 5;
    private static final int NB_CARTES_A_PIOCHER = 2;

    public Aiguillage()
    {
        super("Aiguillage", COUT_AIGUILLAGE, TypeCarte.ACTION);
    }

    @Override
    public void jouer(Joueur joueur)
    {
        if (joueur.getArgent() >= COUT_AIGUILLAGE)
        {
            if (joueur.getPioche().size() >= NB_CARTES_A_PIOCHER)
            {
                List<Carte> cartesPiochees = joueur.piocher(NB_CARTES_A_PIOCHER);

                joueur.getMain().addAll(cartesPiochees);

                joueur.setArgent(joueur.getArgent() - COUT_AIGUILLAGE);
            }
            else
            {
                joueur.log("La pioche ne contient pas suffisamment de cartes pour jouer l'Aiguillage.");
            }
        }
        else
        {
            joueur.log("Vous n'avez pas assez d'argent pour jouer l'Aiguillage.");
        }
    }
}
