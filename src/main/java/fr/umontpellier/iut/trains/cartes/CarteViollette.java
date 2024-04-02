package fr.umontpellier.iut.trains.cartes;

public abstract class CarteViollette extends Carte
{
    private final String type;

    @Override
    public String getType() {
        return type;
    }

    public CarteViollette (String nom, int prix)
    {
        super(nom,prix);
        this.type = "Gare";
    }


}
