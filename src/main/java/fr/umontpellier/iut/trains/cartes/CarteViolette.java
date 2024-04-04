package fr.umontpellier.iut.trains.cartes;

public abstract class CarteViolette extends Carte
{
    private final String type;

    @Override

    public String getType() {
        return type;
    }

    public CarteViolette (String nom, int prix)
    {
        super(nom,prix);
        this.type = "Gare";
    }

    public CarteViolette(String nom, int prix, int valeur) {
        super(nom, prix, valeur);
        this.type = "Gare";
    }
}
