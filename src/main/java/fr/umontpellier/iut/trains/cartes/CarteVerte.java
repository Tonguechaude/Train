package fr.umontpellier.iut.trains.cartes;

public abstract class CarteVerte extends Carte
{

    private final String type;

    @Override
    public String getType() {
        return type;
    }

    public CarteVerte(String nom, int prix) {
        super(nom, prix);
        this.type = "Rail";

    }
}
