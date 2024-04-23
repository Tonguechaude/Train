package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class Aiguillage extends CarteRouge
{
    public Aiguillage()
    {
        super("Aiguillage", 5,0);
    }

    @Override
    public void jouer(Joueur joueur)
    {
        joueur.setMain(joueur.piocher(2));
    }
}
