package fr.umontpellier.iut.trains.cartes;

public class CarteDoubleType extends Carte{

    private final String type;

    private final String secondType;

    public CarteDoubleType(String nom, int prix, int valeur,String type, String secondType) {
        super(nom, prix, valeur);
        this.type = type;
        this.secondType = secondType;
    }

    public String getType() { return type;}

    @Override
    public String getSecondType() { return secondType;}

}
