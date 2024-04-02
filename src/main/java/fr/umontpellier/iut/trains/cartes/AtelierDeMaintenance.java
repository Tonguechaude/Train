package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class AtelierDeMaintenance extends CarteRouge {
    public AtelierDeMaintenance() {
        super("Atelier de maintenance", 5);
    }

    public void jouer(Joueur joueur)
    {
        // Recherche d'une carte de type train dans la main du joueur
        Carte carteDevoilee = null;
        for (Carte carte : joueur.getMain())
        {
            if (carte.getType().equals("Train"))
            {
                carteDevoilee = carte;
                break;  // On arrête la recherche dès qu'une carte de type train est trouvée
            }
        }

        if (carteDevoilee != null)
        {
            // Tente de réserver une carte identique depuis la réserve
            Carte carteReserve = joueur.getJeu().prendreDansLaReserve(carteDevoilee.getNom());
            if (carteReserve != null)
            {
                joueur.addCarteRecue(carteReserve);
                joueur.getJeu().log(String.format("%s a réservé une carte identique à %s depuis la réserve.", joueur.getNom(), carteDevoilee.getNom()));
            }
            else
            {
                joueur.getJeu().log("Il n'y a plus de cartes identiques disponibles dans la réserve.");
            }
        }
        else
        {
            joueur.getJeu().log("Aucune carte de type train dans la main du joueur.");
        }
    }
}
