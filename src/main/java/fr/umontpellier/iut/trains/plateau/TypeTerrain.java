package fr.umontpellier.iut.trains.plateau;

/**
 * Enumération des différents types de terrain des tuiles du plateau
 */
public enum TypeTerrain {
    PLAINE, MONTAGNE, FLEUVE,ETOILE,VILLE,MER,JOUEUR,TOUTYPE;

    public int valeurTerrain() {
        if(this == FLEUVE)
        {
            return 1;
        }
        else if(this == MONTAGNE)
        {
            return 2;
        }
        else if(this == MER)
        {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return switch(this) {
            case FLEUVE -> "FLEUVE";
            case MONTAGNE -> "MONTAGNE";
            case PLAINE -> "PLAINE";
            case ETOILE -> "ETOILE";
            case VILLE -> "VILLE";
            case MER -> "MER";
            case JOUEUR -> "JOUEUR";
            case TOUTYPE -> "TOUTYPE";
        };
    }
}
