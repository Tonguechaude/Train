package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Cooperation extends CarteVerte {
    public Cooperation() {
        super("Coopération", 5, 0);
    }

    public void jouer (Joueur joueur)
    {
        joueur.addPointDeRails(1); // Incrémente le nombre de pointsRails du joueur de 1

        // Vérifie si le joueur est en train de construire sur une montagne où se trouvent d'autres joueurs
        // Si oui, applique le surcoût de 2¥ lié à la montagne
        //if (/* condition pour vérifier si le joueur construit sur une montagne avec d'autres joueurs */) {
            joueur.addArgent(-2); // Retire 2¥ du joueur pour le surcoût lié à la montagne
        //}

        // Le joueur ne reçoit pas de carte FERRAILLE liée à la construction sur une case où se trouvent d'autres joueurs,
        // mais il reçoit normalement les FERRAILLE provenant d'autres effets pendant son tour.
        // (La gestion des FERRAILLE provenant d'autres effets n'est pas implémentée ici, mais cela pourrait être ajouté)

        // Autres actions spécifiques à la carte Coopération...




    }


}
