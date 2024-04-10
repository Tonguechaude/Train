package fr.umontpellier.iut.trains.cartes;

public abstract class CarteGrise extends Carte
{
    private final String type;

    @Override
    public String getType() {
        return type;
    }

    CarteGrise(String nom, int prix)
    {
        super(nom,prix);
        this.type = "Ferraille";
    }

    public CarteGrise(String nom, int prix, int valeur) {
        super(nom, prix, valeur);
        this.type = "Ferraille";
    }
}
