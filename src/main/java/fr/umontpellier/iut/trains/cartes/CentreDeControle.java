package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CentreDeControle extends CarteRouge {
    public CentreDeControle() {
        super("Centre de contrôle", 3, 0);
    }

    public void jouer (Joueur joueur)
    {
        joueur.ajouterCarteMain(joueur.piocher());

        List<String> choix = new ArrayList<>();
        List<Bouton> listeBouton = new ArrayList<>();

        /* Alternative pour creer tout les boutons */

        for (Carte c : joueur.getMain())
        {
            if (!choix.contains(c))
            {
                choix.add(c.getNom());
                listeBouton.add(new Bouton(c.getNom()));
            }
        }

        for (Carte c : joueur.getDefausse())
        {
            if (!choix.contains(c))
            {
                choix.add(c.getNom());
                listeBouton.add(new Bouton(c.getNom()));
            }
        }

        for (Carte c : joueur.getPioche())
        {
            if (!choix.contains(c))
            {
                choix.add(c.getNom());
                listeBouton.add(new Bouton(c.getNom()));
            }
        }

        String input = joueur.choisir("Voulez-vous faire ceci ?", null, listeBouton, false);

        Carte pioche2 = joueur.piocher();

        if (pioche2.getNom().equals(input))
        {
            joueur.ajouterCarteMain(pioche2);
        }
        else
        {
            joueur.remettreAuDessusPioche(pioche2);
        }
    }
}


/*List<Bouton> boutons = Arrays.asList(
                new Bouton("Aiguillage !", "Aiguillage"),
                new Bouton("Appartement !", "Appartement"),
                new Bouton("Atelier De Mainteance !", "AtelierDeMaintenance"),
                new Bouton("Bureau Du Chef De Gare !", "BureauDuChefDeGare"),
                new Bouton("Cabine Du Conducteur !", "CabineDuConducteur"),
                new Bouton("Centre De Controle !", "CentreDeControle"),
                new Bouton("Centre De Renseignements !", "CentreDeRenseignements"),
                new Bouton("Cooperation !", "Cooperation"),
                new Bouton("Decharge !", "Decharge"),
                new Bouton("Depot !", "Depot"),
                new Bouton("Depotoir !", "Depotoir"),
                new Bouton("Echangeur !", "Echangeur"),
                new Bouton("Feraille !", "Feraille"),
                new Bouton("Ferronerie !", "Ferronerie"),
                new Bouton("Feu De Signalisation !", "FeuDeSignalisation"),
                new Bouton("Gare !", "Gare"),
                new Bouton("Gratte Ciel !", "GratteCiel"),
                new Bouton("Horaires Estivaux !", "HorairesEstivaux"),
                new Bouton("Horaires Temporaires !", "HorairesTemporaires"),
                new Bouton("Immeuble !", "Immeuble"),
                new Bouton("Parc D'attractions !", "ParcDAttractions"),
                new Bouton("Passage en Gare !", "PassageEnGare"),
                new Bouton("Personnel de Gare !", "PersonnelDeGare"),
                new Bouton("Pont en Acier !", "PontEnAcier"),
                new Bouton("Pose de Rails !", "PoseDeRails"),
                new Bouton("Remorquage !", "Remorquage"),
                new Bouton("Salle de Controle !", "SalleDeControle"),
                new Bouton("TGV !", "TGV"),
                new Bouton("Train de Marchandise !", "TrainDeMarchandises"),
                new Bouton("Train de Tourisme !", "TrainDeTourisme"),
                new Bouton("Train Direct !", "TrainDirect"),
                new Bouton("Train Express !", "TrainExpress"),
                new Bouton("Train Matinal !", "TrainMatinal"),
                new Bouton("Train Omnibus !", "TrainOmnibus"),
                new Bouton("Train Postal !", "TrainPostal"),
                new Bouton("Tunnel !", "Tunnel"),
                new Bouton("Usine de Wagons !", "UsineDeWagons"),
                new Bouton("Viaduc !", "Viaduc"),
                new Bouton("Voie Souterraine !", "VoieSouterraine")
                );*/