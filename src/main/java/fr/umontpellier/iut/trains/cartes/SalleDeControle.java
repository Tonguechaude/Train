package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class SalleDeControle extends CarteRouge {
    public SalleDeControle() {
        super("Salle de contrôle",7);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.setMain(joueur.piocher(3));
        joueur.getJeu().log(String.format("%s pioche 3 cartes.",joueur.getNom()));
    }
}
