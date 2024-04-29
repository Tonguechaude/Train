package fr.umontpellier.iut.trains.plateau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.umontpellier.iut.trains.Joueur;

public abstract class Tuile {
    /**
     * Liste des tuiles voisines qui sont connectées à la tuile courante (voisines
     * sur le plateau, sauf les tuiles entre lesquelles il y a une barrière
     * infranchissable)
     */
    private ArrayList<Tuile> voisines;

    protected TypeTerrain typeTerrain;
    /**
     * Ensemble des joueurs qui ont posé un rail sur la tuile
     */
    private Set<Joueur> rails;

    public Tuile() {
        this.voisines = new ArrayList<>();
        this.rails = new HashSet<>();
    }

    /**
     * Analyse la somme d'argent nécessaire au joueur pour poser un rail sur la tuile (this).
     * Ne prends pas en compte si le joueur peut poser un rail dessus ou non.
     *
     * @param joueur le joueur qui veut poser un rail sur la tuile
     * @return le surcout pour la pose d'un rail par rapport au joueur
     */
    public int surcoutPoseDeRail(Joueur joueur){
        if(joueur.getListReductions().contains(TypeTerrain.TOUTYPE))
        {
            return -1;
        } else if(joueur.getListReductions().contains(TypeTerrain.JOUEUR))
        {
            return 0;
        }
        return getNombreRails();
    }

    /**
     * Retourne 0 par défaut, seul les tuiles villes et étoiles octroient des points de victoire (@override).
     * @param joueur joueur auquel les points de victoires sont associés
     * @return les points de victoire octroyés au joueur selon la tuile
     */
    public int getPointVictoire(Joueur joueur){
        return 0;
    }

    /**
     * Seules les tuiles mer, lieu éloigné ou contenant un joueur retournent false.
     * @return vrai si la tuile est éligible pour l'initialisation, false sinon
     */
    public boolean tuileInitialisation() {
        return rails.isEmpty();
    }

    /**
     * vérifie si le {@code joueur} est en mesure de poser un rail sur la tuile en vérifiant certains critères:
     * <ul>
     * <li> si le {@code joueur} possède assez de jetons rails </li>
     * <li> si la tuile est disponible a la pose de rail </li>
     * <li> si le {@code joueur} ne possède pas déjà cette tuile </li>
     * <li> si le {@code joueur} possède assez d'argent
     * <li> si le {@code joueur} possède un rail a proximité pour faire en sorte que ce soit le prolongement de son réseau féroviaire </li>
     * </ul>
     * <p>
     * La vérification des {@code pointsRails} se fait directement dans jouerTour.
     * </p>
     * @param joueur le joueur dont on veut vérifier la légitimité à poser le rail
     * @return true si le joueur peut poser un rail, false sinon
     */
    public boolean peutPoserRail(Joueur joueur) {
        if(joueur.getNbJetonsRails() < 1)
        {
            joueur.log("vous ne possédez pas assez de jetons rails");
            return false;
        }

        if (this.surcoutPoseDeRail(joueur) < 0)
        {
            return false;
        }

        if(hasRail(joueur))
        {
            joueur.log("vous possédez déjà un rail sur cette case");
            return false;
        }

        if(!joueur.isRichEnough(surcoutPoseDeRail(joueur)))
        {
            return false;
        }

        for(Tuile tuile : voisines)
        {
            if(tuile.hasRail(joueur)){
                return true;
            }
        }
        joueur.log("Cette tuile est trop loin de votre réseau féroviaire pour y construire un rail");
        return false;
    }

    /**
     * @return {@code true} si la tuile ne contient aucun rail, {@code false} sinon
     */
    public boolean estVide() {
        return rails.isEmpty();
    }

    /**
     * @param joueur le joueur dont on veut déterminer s'il a posé un rail sur la
     *               tuile
     * @return {@code true} si le joueur a posé un rail sur la tuile, {@code false}
     *         sinon
     */
    public boolean hasRail(Joueur joueur) {
        return rails.contains(joueur);
    }

    /**
     * Ajoute un rail du joueur sur la tuile
     * 
     * @param joueur le joueur qui pose un rail sur la tuile
     */
    public void ajouterRail(Joueur joueur) {
        rails.add(joueur);
    }

    /**
     * Ajoute une voisine à la tuile courante, et ajoute la tuile courante comme
     * voisine de la tuile passée en argument.
     * <p>
     * Cette méthode est appelée par la méthode {@code Plateau.makeTuiles()} pour
     * construire le plateau de jeu.
     * 
     * @param tuile la tuile voisine à ajouter
     */
    public void ajouterVoisine(Tuile tuile) {
        voisines.add(tuile);
        tuile.voisines.add(this);
    }

    /**
     * Supprime une tuile de la liste de voisines de {@code this} (et supprime
     * {@code this} des voisines de la tuile passée en paramètres).
     * <p>
     * Cette méthode est appelée par la méthode {@code Plateau.makeTuiles()} pour
     * représenter les barrières infranchissables sur le plateau.
     * 
     * @param tuile la tuile voisine à supprimer
     */
    public void supprimerVoisine(Tuile tuile) {
        voisines.remove(tuile);
        tuile.voisines.remove(this);
    }

    /**
     * @return le nombre de jetons gare posés sur la tuile. Par défaut la fonction
     *         renvoie 0 car on ne peut pas poser de jeton gare sur une tuile
     *         quelconque.
     */
    public int getNbGares() {
        return 0;
    }

    /**
     * @return le nombre de rails posés sur la Tuile pour ensuite calculer le surcout à appliquer
     */
    public int getNombreRails() {
        return rails.size();
    }

    /**
     * @return une représentation de la tuile sous la forme d'un dictionnaire de
     *         valeurs sérialisables (qui sera converti en JSON pour l'envoyer à
     *         l'interface
     *         graphique)
     */
    public Map<String, Object> dataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("rails", rails.stream().map(Joueur::getCouleur).toArray());
        int nbGares = getNbGares();
        if (nbGares > 0) {
            map.put("nbGares", nbGares);
        }
        return map;
    }

    public TypeTerrain getType() {
        return typeTerrain;
    }
}
