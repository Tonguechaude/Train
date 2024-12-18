package fr.umontpellier.iut.trains.cartes;

public abstract class CarteRouge extends Carte
{
    private final String type;

    @Override
    public String getType() {
        return type;
    }

    public CarteRouge(String nom, int prix) {
        super(nom, prix);
        this.type = "Action";
    }

    public CarteRouge(String nom, int prix, int valeur) {
        super(nom, prix, valeur);
        this.type = "Action";
    }
}
