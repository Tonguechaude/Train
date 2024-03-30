package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class AtelierDeMaintenance extends Carte {
    public AtelierDeMaintenance() {
        super("Atelier de maintenance", 5, TypeCarte.ACTION);
    }

    public void jouer(Joueur joueur)
    {
        if(joueur.getArgent() >= 5) {
            joueur.setArgent(joueur.getArgent() - 5);

            Carte carteDevoilee = null;
            boolean carteTrouver = false;
            for (Carte carte : joueur.getMain()) {
                if (carte.getType() == TypeCarte.TRAINS && !carteTrouver) {
                    carteTrouver = true;
                    carteDevoilee = carte;
                }
            }

            if (carteDevoilee != null) {
                Carte carteReserve = joueur.getJeu().prendreDansLaReserve(carteDevoilee.getNom());
                if (carteReserve != null) {
                    joueur.addCarteRecue(carteReserve);
                    joueur.getJeu().log(String.format("%s a réservé une carte identique à %s depuis la réserve.", joueur.getNom(), carteDevoilee.getNom()));
                } else {
                    joueur.getJeu().log("Il n'y a plus de cartes identiques disponibles dans la réserve.");
                }
            } else {
                joueur.getJeu().log("Aucune carte de type train dans la main du joueur.");
            }
        }
        else
        {
            joueur.getJeu().log("Pas assez d'argent pour utiliser l'Atelier de Maintenance.");
        }
    }


}
