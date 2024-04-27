package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class Carte {
    private final String nom;
    private int prix = 0;
    private int valeur = 0;

    private String type;

    /**
     * Constructeur simple
     * <p>
     * Important : La classe Carte est actuellement très incomplète. Vous devrez
     * ajouter des attributs et des méthodes et donc probablement définir au moins
     * un autre constructeur plus complet. Les sous-classes de Cartes qui vous sont
     * fournies font appel à ce constructeur simple mais au fur et à mesure que vous
     * les compléterez, elles devront utiliser les autres constructeurs de Carte. Si
     * vous n'utilisez plus ce constructeur, vous pouvez le supprimer.
     *
     * @param nom
     */
    public Carte(String nom) {
        this.nom = nom;
    }

    public Carte (String nom, int prix)
    {
        this(nom);
        this.prix = prix;
    }

    public Carte(String nom, int prix, int valeur) {
        this(nom,prix);
        this.valeur = valeur;
    }

    /**
     * gère les points victoires contenus par une carte (renvoie 0 par défaut, seul les cartes jaunes en contiennent)
     * @return le nombre de points victoires contenus par la carte
     */
    public int getPointVictoire() {
        return 0;
    }

    public String getNom() {
        return nom;
    }

    public int getPrix() {
        return prix;
    }

    public int getValeur() {
        return valeur;
    }

    /**
     * Cette fonction est exécutée lorsqu'un joueur joue la carte pendant son tour.
     * Toutes les cartes ont une méthode jouer, mais elle ne fait rien par défaut.
     *
     * @param joueur le joueur qui joue la carte
     */
    public void jouer(Joueur joueur) {}

    @Override
    public String toString() {
        return nom;
    }


    public abstract String getType();

    public String getSecondType() {
        return "";
    }
}
