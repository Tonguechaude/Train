package fr.umontpellier.iut.trains.cartes;

public abstract class CarteBleu extends Carte
{

    private final String type;

    @Override
    public String getType() {
        return type;
    }
    public CarteBleu(String nom, int prix) {
        super(nom, prix);
        this.type = "Train";
    }
}
