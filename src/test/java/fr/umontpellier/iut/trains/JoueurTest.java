package fr.umontpellier.iut.trains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import fr.umontpellier.iut.trains.cartes.*;
import org.junit.jupiter.api.Test;

public class JoueurTest extends BaseTestClass
{

    @Test
    void test_piocheFaible_defausseVide() {
        setupJeu();
        initialisation();

        Carte omni = new TrainOmnibus();
        Carte gare = new Gare();
        Carte fondPioche = new Ferraille();

        addAll(pioche, omni);
        addAll(defausse, gare, fondPioche);

        List<Carte> Cartes = joueur.piocher(4);

        assertTrue(containsSame(Cartes,omni,gare,fondPioche));
    }

}
