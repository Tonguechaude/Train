package fr.umontpellier.iut.trains.cartes;

public abstract class CarteJaune extends Carte
{
    private final String type;

    private int pointVictoire;

    @Override
    public String getType() {
        return type;
    }


    public CarteJaune(String nom, int prix) {
        super(nom, prix);
        this.type = "Victoire";
    }

    public CarteJaune(String nom, int prix, int valeur) {
        super(nom, prix, valeur);
        this.type = "Victoire";
    }

    public CarteJaune(String nom, int prix, int valeur, int pointVictoire) {
        super(nom, prix, valeur);
        this.type = "Victoire";
        this.pointVictoire = pointVictoire;
    }

}
