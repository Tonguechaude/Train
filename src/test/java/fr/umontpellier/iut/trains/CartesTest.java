package fr.umontpellier.iut.trains;

import fr.umontpellier.iut.trains.cartes.*;
import fr.umontpellier.iut.trains.plateau.TuileVille;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartesTest extends BaseTestClass {

    @Test
    void test_aiguillage_ajoute_1() {
        setupJeu("Aiguillage");
        initialisation();

        Carte c = new Aiguillage();
        Carte gare1 = new Gare();

        addAll(main, c);
        addAll(pioche, gare1);

        jouerTourPartiel("Aiguillage");

        assertTrue(containsReferences(main, gare1));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_aiguillage_pioche_vide() {
        setupJeu("Aiguillage");
        initialisation();

        Carte c = new Aiguillage();

        addAll(main, c);
        addAll(pioche);

        jouerTourPartiel("Aiguillage");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_Salle_de_contrôle_pioche_vide() {
        setupJeu("Salle de contrôle");
        initialisation();

        Carte c = new SalleDeControle();

        addAll(main, c);
        addAll(pioche);

        jouerTourPartiel("Salle de contrôle");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_Passage_en_gare_pioche_vide() {
        setupJeu("Passage en gare");
        initialisation();

        Carte c = new PassageEnGare();

        addAll(main, c);
        addAll(pioche);

        jouerTourPartiel("Passage en gare");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_salledecontrole() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fondPioche = new Ferraille();
        Carte aig = new PassageEnGare();
        Carte gare1 = new Gare();
        Carte gare2 = new Gare();
        Carte gare3 = new Gare();

        addAll(main, c, aig);
        addAll(pioche, gare1, gare2, gare3, fondPioche);
        jouerTourPartiel("Bureau du chef de gare", "Passage en gare");

        assertTrue(containsReferences(main, aig, gare1));
        assertTrue(containsReferencesInOrder(pioche, gare2, gare3, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_passageengare() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fondPioche = new Ferraille();
        Carte aig = new SalleDeControle();
        Carte gare1 = new Gare();
        Carte gare2 = new Gare();
        Carte gare3 = new Gare();

        addAll(main, c, aig);
        addAll(pioche, gare1, gare2, gare3, fondPioche);
        jouerTourPartiel("Bureau du chef de gare", "Salle de contrôle");

        assertTrue(containsReferences(main, aig, gare1, gare2, gare3));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_traindeMarchandise() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fondPioche = new Ferraille();
        Carte tt = new TrainDeMarchandises();
        Carte f1 = new Ferraille();
        Carte f2 = new Ferraille();
        Carte f3 = new Ferraille();
        int nbFerraille = reserve.get("Ferraille").size();

        addAll(main, c, tt,  f1, f2, f3);
        addAll(pioche, fondPioche);
        jouerTourPartiel("Bureau du chef de gare", "Train de marchandises", "Ferraille", "Ferraille", "");

        assertTrue(containsReferences(main, tt, f3));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertEquals(reserve.get("Ferraille").size(), nbFerraille + 2);
    }

    @Test
    void test_TraindeMarchandise_2ferraille() {
        setupJeu("Train de marchandises");
        initialisation();

        Carte fondPioche = new Ferraille();
        Carte c = new TrainDeMarchandises();
        Carte f1 = new Ferraille();
        Carte f2 = new Ferraille();
        Carte f3 = new Ferraille();
        int nbFerraille = reserve.get("Ferraille").size();

        addAll(main, c,  f1, f2, f3);
        addAll(pioche, fondPioche);
        jouerTourPartiel("Train de marchandises", "Ferraille", "Ferraille", "");

        assertTrue(containsReferences(main, f3));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(3, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertEquals(reserve.get("Ferraille").size(), nbFerraille + 2);
    }

    @Test
    void test_TraindeMarchandise_6ferraille() {
        setupJeu("Train de marchandises");
        initialisation();

        Carte fondPioche = new Ferraille();
        Carte c = new TrainDeMarchandises();
        Carte f1 = new Ferraille();
        Carte f2 = new Ferraille();
        Carte f3 = new Ferraille();
        Carte f4 = new Ferraille();
        Carte f5 = new Ferraille();
        Carte f6 = new Ferraille();
        int nbFerraille = reserve.get("Ferraille").size();

        addAll(main, c, f1, f2, f3, f4, f5, f6);
        addAll(pioche, fondPioche);
        jouerTourPartiel("Train de marchandises", "Ferraille", "Ferraille", "Ferraille", "Ferraille", "Ferraille", "Ferraille", "");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(7, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertEquals(reserve.get("Ferraille").size(), nbFerraille + 6);
    }

    @Test
    void test_bureau_du_chef_de_gare_TGV_avec_Omni() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fondPioche = new Ferraille();
        Carte tt = new TGV();
        Carte omni = new TrainOmnibus();

        addAll(main, c, tt, omni);
        addAll(pioche, fondPioche);
        jouerTourPartiel("Train omnibus", "Bureau du chef de gare" ,"TGV");

        assertTrue(containsReferences(main, tt));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_TGV_avec_2Omni() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fondPioche = new Ferraille();
        Carte tt = new TGV();
        Carte omni = new TrainOmnibus();
        Carte omni2 = new TrainOmnibus();

        addAll(main, c, tt, omni, omni2);
        addAll(pioche, fondPioche);
        jouerTourPartiel("Train omnibus" ,"Train omnibus", "Bureau du chef de gare", "TGV");

        assertTrue(containsReferences(main, tt));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni, omni2));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(3, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_TGV_sans_Omni() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fondPioche = new Ferraille();
        Carte tt = new TGV();

        addAll(main, c, tt);
        addAll(pioche, fondPioche);
        jouerTourPartiel("Bureau du chef de gare", "TGV");

        assertTrue(containsReferences(main, tt));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_Postal() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte tt = new TrainPostal();
        Carte im = new Immeuble();
        Carte f = new Ferraille();

        addAll(main, c, tt, im, f);
        addAll(pioche);
        jouerTourPartiel("Bureau du chef de gare", "Train postal", "Immeuble", "Ferraille", "");

        assertTrue(containsReferences(main, tt));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse, im, f));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_train_Postal_ferraille() {
        setupJeu("Train postal");
        initialisation();

        Carte c = new TrainPostal();
        Carte im = new Immeuble();
        Carte f = new Ferraille();

        addAll(main, c, im, f);
        addAll(pioche);
        jouerTourPartiel( "Train postal", "Immeuble", "Ferraille", "");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse, im, f));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(3, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_traindetourisme() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fondPioche = new Ferraille();
        Carte tt = new TrainDeTourisme();

        addAll(main, c, tt);
        addAll(pioche, fondPioche);
        jouerTourPartiel("Bureau du chef de gare", "Train de tourisme");

        assertTrue(containsReferences(main, tt));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertEquals(1, joueur.getScoreTotal());
    }

    @Test
    void test_bureau_du_chef_de_gare_cabine_du_conducteur_0() {
        setupJeu("Bureau du chef de gare", "Cabine du conducteur");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte cc = new CabineDuConducteur();

        addAll(main, c, cc);
        addAll(pioche);

        jouerTourPartiel("Bureau du chef de gare", "Cabine du conducteur", "");

        assertTrue(containsReferences(main, cc));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_cabine_du_conducteur_1() {
        setupJeu("Cabine du conducteur", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte cc = new CabineDuConducteur();
        Carte fondPioche = new Ferraille();
        Carte omni1 = new TrainOmnibus();
        Carte gare1 = new Gare();

        addAll(main, c, cc, omni1);
        addAll(pioche, gare1, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Cabine du conducteur", "Train omnibus", "");

        assertTrue(containsReferences(main, gare1, cc));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, omni1));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_cabine_du_conducteur_2() {
        setupJeu("Cabine du conducteur", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte cc = new CabineDuConducteur();
        Carte fondPioche = new Ferraille();
        Carte omni1 = new TrainOmnibus();
        Carte omni2 = new TrainOmnibus();
        Carte omni3 = new TrainOmnibus();
        Carte gare1 = new Gare();
        Carte gare2 = new Gare();

        addAll(main, c, omni1, omni2, omni3, cc);
        addAll(pioche, gare1, gare2, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Cabine du conducteur", "Train omnibus", "Train omnibus", "");

        assertTrue(containsReferences(main, omni3, gare1, gare2, cc));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, omni1, omni2));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_ateliermaintenance() {
        setupJeu("Atelier de maintenance");
        initialisation();

        Carte bu = new BureauDuChefDeGare();
        Carte c = new AtelierDeMaintenance();
        Carte fondPioche = new Ferraille();
        Carte expr1 = new TrainExpress();
        Carte expr2 = reserve.get("Train express").get(0);

        addAll(main, c, expr1, bu);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Atelier de maintenance", "Train express");

        assertTrue(containsReferences(main, expr1, c));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, bu));
        assertTrue(containsReferences(cartesRecues, expr2));
        assertFalse(containsReference(reserve.get("Train express"), expr2));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_atelier_maintenance_pas_possibles() {
        setupJeu("Atelier de maintenance", "Train omnibus");
        initialisation();

        Carte c = new AtelierDeMaintenance();
        Carte fondPioche = new Ferraille();
        for (int i = 0; i < 10; i++) {
            joueurs.get(0).getJeu().prendreDansLaReserve("Train omnibus");
        }
        Carte omni = new TrainOmnibus();

        addAll(main, c, omni);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Atelier de maintenance", "Train omnibus");

        assertTrue(containsReferences(main, omni));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, reserve.get("Train omnibus").size());
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    /*@Test
    void test_usine_de_wagon_pas_possibles() {
        setupJeu("Usine de wagons");
        initialisation();

        Carte c = new UsineDeWagons();
        Carte fondPioche = new Ferraille();
        Carte omni = new TrainOmnibus();
        Carte direct = reserve.get("Train direct").get(0);

        addAll(main, c, omni);
        addAll(pioche, fondPioche);

        try {
            jouerErreurTourPartiel("Usine de wagons", "Train omnibus", "ACHAT:Train direct");
            fail("L'exception IndexOutOfBoundsException n'a pas été levée");
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Erreur attrapée : " + e.getMessage());
        }
    }*/

    @Test
    void test_bureau_du_chef_de_gare_remorquage() {
        setupJeu("Remorquage", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte r = new Remorquage();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte omni = new TrainOmnibus();
        Carte expr = new TrainExpress();
        Carte imm = new Immeuble();

        addAll(main, c, r);
        addAll(pioche, fondPioche);
        addAll(defausse, gare, omni, expr, imm);

        jouerTourPartiel("Bureau du chef de gare", "Remorquage", "Train express");

        assertTrue(containsReferences(main, expr, r));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, gare, omni, imm));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_depotoir_poserrails_avec_adv_compare_le_nombre() {
        setupJeu("Dépotoir");
        initialisation();
        tuiles.get(22).ajouterRail(joueur);
        tuiles.get(23).ajouterRail(joueurs.get(0));

        Carte c = new Depotoir();
        Carte pr = new PoseDeRails();
        Carte epx = new TrainExpress();
        int f = reserve.get("Ferraille").count("Ferraille");

        addAll(main, c, pr, epx);

        jouerTourPartiel("Train express", "Dépotoir", "Pose de rails", "TUILE:23");

        checkPlateau(List.of(23), List.of(22, 23), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, pr, epx));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(f,reserve.get("Ferraille").count("Ferraille"));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_de_chef_de_gare_depotoir_poserrails_avec_adv_compare_le_nombre() {
        setupJeu("Dépotoir", "Bureau du chef de gare");
        initialisation();
        tuiles.get(22).ajouterRail(joueur);
        tuiles.get(23).ajouterRail(joueurs.get(0));

        Carte c = new BureauDuChefDeGare();
        Carte de = new Depotoir();
        Carte pr = new PoseDeRails();
        Carte epx = new TrainExpress();
        int f = reserve.get("Ferraille").count("Ferraille");

        addAll(main, c, pr, epx, de);

        jouerTourPartiel( "Train express", "Bureau du chef de gare", "Dépotoir", "Pose de rails", "TUILE:23");

        checkPlateau(List.of(23), List.of(22, 23), null);
        assertTrue(containsReferences(main, de));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, pr, epx));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(f,reserve.get("Ferraille").count("Ferraille"));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_traindetmatinal_oui() {
        setupJeu("Bureau du chef de gare", "Aiguillage", "Train matinal", "Train omnibus");
        initialisation();

        Carte bu = new BureauDuChefDeGare();
        Carte tt = new TrainMatinal();

        Carte c = reserve.get("Aiguillage").get(0);

        Carte omni1 = new TrainOmnibus();
        Carte omni2 = new TrainOmnibus();
        Carte omni3 = new TrainOmnibus();
        Carte omni4 = new TrainOmnibus();
        Carte omni5 = new TrainOmnibus();

        List<String> instructions = new ArrayList<>();

        for (int i = 0; i < 5 + 1; i++) {
            instructions.add("Train omnibus");
        }

        instructions.add("Bureau du chef de gare");
        instructions.add("Train matinal");
        instructions.add("ACHAT:" + "Aiguillage");
        instructions.add("oui");

        addAll(main, bu, tt, omni1, omni2, omni3, omni4, omni5);
        jouerTourPartiel(instructions);

        assertTrue(containsReferences(main, tt ));
        assertTrue(containsReferencesInOrder(pioche, c));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, bu, omni1, omni2, omni3, omni4, omni5));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_traindetmatinal_non() {
        setupJeu("Bureau du chef de gare", "Aiguillage", "Train matinal", "Train omnibus");
        initialisation();

        Carte bu = new BureauDuChefDeGare();
        Carte tt = new TrainMatinal();

        Carte c = reserve.get("Aiguillage").get(0);

        Carte omni1 = new TrainOmnibus();
        Carte omni2 = new TrainOmnibus();
        Carte omni3 = new TrainOmnibus();
        Carte omni4 = new TrainOmnibus();
        Carte omni5 = new TrainOmnibus();

        List<String> instructions = new ArrayList<>();

        for (int i = 0; i < 5 + 1; i++) {
            instructions.add("Train omnibus");
        }

        instructions.add("Bureau du chef de gare");
        instructions.add("Train matinal");
        instructions.add("ACHAT:" + "Aiguillage");
        instructions.add("non");

        addAll(main, bu, tt, omni1, omni2, omni3, omni4, omni5);
        jouerTourPartiel(instructions);

        assertTrue(containsReferences(main, tt ));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, bu,  omni1, omni2, omni3, omni4, omni5));
        assertTrue(containsReferences(cartesRecues, c));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_raindetmatinal_oui() {
        setupJeu("Train matinal", "Aiguillage", "Train omnibus");
        initialisation();

        Carte tt = new TrainMatinal();

        Carte c = reserve.get("Aiguillage").get(0);

        Carte omni1 = new TrainOmnibus();
        Carte omni2 = new TrainOmnibus();
        Carte omni3 = new TrainOmnibus();
        Carte omni4 = new TrainOmnibus();
        Carte omni5 = new TrainOmnibus();

        List<String> instructions = new ArrayList<>();
        for (int i = 0; i < 5 + 1; i++) {
            instructions.add("Train omnibus");
        }
        instructions.add("Train matinal");
        instructions.add("ACHAT:" + "Aiguillage");
        instructions.add("oui");

        addAll(main, tt, omni1, omni2, omni3, omni4, omni5);
        jouerTourPartiel(instructions);

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, c));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, tt, omni1, omni2, omni3, omni4, omni5));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_raindetmatinal_non() {
        setupJeu("Train matinal", "Aiguillage", "Train omnibus");
        initialisation();

        Carte tt = new TrainMatinal();
        Carte c = reserve.get("Aiguillage").get(0);

        Carte omni1 = new TrainOmnibus();
        Carte omni2 = new TrainOmnibus();
        Carte omni3 = new TrainOmnibus();
        Carte omni4 = new TrainOmnibus();
        Carte omni5 = new TrainOmnibus();

        List<String> instructions = new ArrayList<>();

        for (int i = 0; i < 5 + 1; i++) {
            instructions.add("Train omnibus");
        }
        instructions.add("Train matinal");
        instructions.add("ACHAT:" + "Aiguillage");
        instructions.add("non");

        addAll(main, tt, omni1, omni2, omni3, omni4, omni5);
        jouerTourPartiel(instructions);

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, tt, omni1, omni2, omni3, omni4, omni5));
        assertTrue(containsReferences(cartesRecues, c));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_echangeur() {
        setupJeu("Bureau du chef de gare", "Échangeur", "Train express");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fondPioche = new Ferraille();
        Carte ech = new Echangeur();
        Carte expr = new TrainExpress();

        addAll(main, c, expr, ech);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train express", "Bureau du chef de gare", "Échangeur", "Train express");

        assertTrue(containsReferences(main, ech));
        assertTrue(containsReferencesInOrder(pioche, expr, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_ferronerie() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fero = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, fero, pr);
        jouerTourPartiel("Bureau du chef de gare", "Ferronnerie", "Pose de rails");

        assertTrue(containsReferences(main, fero));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fero = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, fero, pr);
        jouerTourPartiel("Bureau du chef de gare", "Ferronnerie", "Pose de rails");

        assertTrue(containsReferences(main, fero));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_ferronerie() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte fero1 = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, fero1, pr);
        jouerTourPartiel("Ferronnerie", "Pose de rails");

        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, pr, fero1));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(3, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_ferronerie_plusieurs_rails() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte fero1 = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte pt = new PontEnAcier();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, fero1, pr, pt);
        jouerTourPartiel("Ferronnerie", "Pose de rails", "Pont en acier");

        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, pr, fero1, pt));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(5, getArgent(joueur));
        assertEquals(2, getPointsRails(joueur));
    }

    @Test
    void test_ferronerie_plusieurs_carteverte() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte fero1 = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte pt = new PontEnAcier();
        Carte vi = new Viaduc();
        Carte tu = new Tunnel();
        Carte vo = new VoieSouterraine();
        Carte co = new Cooperation();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, fero1, pr, pt, vi, tu, vo, co);
        jouerTourPartiel("Ferronnerie", "Pose de rails", "Pont en acier", "Tunnel", "Viaduc", "Voie souterraine", "Coopération");

        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, pr, fero1, pt, vi, tu, vo, co));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(13, getArgent(joueur));
        assertEquals(6, getPointsRails(joueur));
    }

    @Test
    void test_ferronerie_effet_cumuler() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte fero1 = new Ferronnerie();
        Carte fero2 = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, fero1, fero2, pr);
        jouerTourPartiel("Ferronnerie", "Ferronnerie", "Pose de rails");

        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, pr, fero1, fero2));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(6, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_ferronerie_effet_cumuler2() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte fero1 = new Ferronnerie();
        Carte fero2 = new Ferronnerie();
        Carte fero3 = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, fero1, fero2, fero3, pr);
        jouerTourPartiel("Ferronnerie", "Ferronnerie", "Ferronnerie" , "Pose de rails");

        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, pr, fero1, fero2, fero3));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(9, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_ferronerie_effet_cumuler3() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte fero1 = new Ferronnerie();
        Carte fero2 = new Ferronnerie();
        Carte fero3 = new Ferronnerie();
        Carte fero4 = new Ferronnerie();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, fero1, fero2, fero3, fero4 , pr);
        jouerTourPartiel("Ferronnerie", "Ferronnerie", "Ferronnerie" , "Ferronnerie" , "Pose de rails");

        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, pr, fero1, fero2, fero3, fero4));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(12, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_pont_en_acier() {
        setupJeu("Pont en acier");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new PontEnAcier();
        Carte omni = new TrainOmnibus();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, omni);
        jouerTourPartiel("Train omnibus", "Pont en acier", "TUILE:33");

        checkPlateau(null, List.of(23, 33), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_sans_pont_en_acier() {
        setupJeu("Pont en acier");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new PoseDeRails();
        Carte omni = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, omni);
        jouerTourPartiel("Train express", "Pose de rails", "TUILE:33");

        checkPlateau(null, List.of(23, 33), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_poser_sur_mer() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(41).ajouterRail(joueur);

        Carte c = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        jouerTourPartiel( "Pose de rails", "TUILE:40");

        checkPlateau(null, List.of(41), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_tunnel_surmontagne() {
        setupJeu("Tunnel");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new Tunnel();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        jouerTourPartiel( "Tunnel", "TUILE:14");

        checkPlateau(null, List.of(23, 14), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_tunnel_sansmontagne() {
        setupJeu("Tunnel");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new Tunnel();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        jouerTourPartiel( "Tunnel", "TUILE:22");

        checkPlateau(null, List.of(23, 22), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_montagne_sanstunnel() {
        setupJeu("Tunnel");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Pose de rails", "TUILE:14");

        checkPlateau(null, List.of(23, 14), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_viaduc_surville() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new Viaduc();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        jouerTourPartiel( "Viaduc", "TUILE:24");

        checkPlateau(null, List.of(23, 24), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_viaduc_sansville() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new Viaduc();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        jouerTourPartiel( "Viaduc", "TUILE:22");

        checkPlateau(null, List.of(23, 22), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_ville_sansviaduc() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Pose de rails", "TUILE:24");

        checkPlateau(null, List.of(23, 24), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_ville_sansviaduc_survilleavecgare() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);
        ((TuileVille) tuiles.get(24)).addGare();

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Pose de rails", "TUILE:24");

        checkPlateau(null, List.of(23, 24), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_ville_sansviaduc_survilleavec2gare() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(32).ajouterRail(joueur);
        ((TuileVille) tuiles.get(42)).addGare();
        ((TuileVille) tuiles.get(42)).addGare();

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte epx1 = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx, epx1);
        jouerTourPartiel( "Train express", "Train express", "Pose de rails", "TUILE:42");

        checkPlateau(null, List.of(32, 42), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx, epx1));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_ville_sansviaduc_survilleavec3gare() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(20).ajouterRail(joueur);
        ((TuileVille) tuiles.get(29)).addGare();
        ((TuileVille) tuiles.get(29)).addGare();
        ((TuileVille) tuiles.get(29)).addGare();

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte epx1 = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx, epx1);
        jouerTourPartiel( "Train express", "Train express", "Pose de rails", "TUILE:29");

        checkPlateau(null, List.of(20, 29), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx, epx1));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_poser_rails_barrière() {
        setupJeu("Viaduc");
        initialisation();
        tuiles.get(42).ajouterRail(joueur);
        ((TuileVille) tuiles.get(42)).addGare();

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Pose de rails", "TUILE:52");

        checkPlateau(null, List.of(42), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_voie_souteraine_surmontagne() {
        setupJeu("Voie souterraine");
        initialisation();
        tuiles.get(21).ajouterRail(joueur);

        Carte c = new VoieSouterraine();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Voie souterraine", "TUILE:20");

        checkPlateau(null, List.of(21, 20), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_voie_souteraine_surville() {
        setupJeu("Voie souterraine");
        initialisation();
        tuiles.get(20).ajouterRail(joueur);
        ((TuileVille) tuiles.get(29)).addGare();
        ((TuileVille) tuiles.get(29)).addGare();
        ((TuileVille) tuiles.get(29)).addGare();

        Carte c = new VoieSouterraine();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Voie souterraine", "TUILE:29");

        checkPlateau(null, List.of(29, 20), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_voie_souteraine_surrivière() {
        setupJeu("Voie souterraine");
        initialisation();
        tuiles.get(23).ajouterRail(joueur);

        Carte c = new VoieSouterraine();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Voie souterraine", "TUILE:32");

        checkPlateau(null, List.of(23, 32), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_voie_souteraine_suretoile() {
        setupJeu("Voie souterraine");
        initialisation();
        tuiles.get(29).ajouterRail(joueur);

        Carte c = new VoieSouterraine();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Voie souterraine", "TUILE:38");

        checkPlateau(null, List.of(29, 38), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_voie_souteraine_surmer() {
        setupJeu("Voie souterraine");
        initialisation();
        tuiles.get(41).ajouterRail(joueur);

        Carte c = new VoieSouterraine();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel( "Train express", "Voie souterraine", "TUILE:50");

        checkPlateau(null, List.of(41), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_TuileEtoiles_4etoiles() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(1).ajouterRail(joueur);

        Carte epx = new TrainExpress();
        Carte epx1 = new TrainExpress();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, epx, epx1, pr);
        jouerTourPartiel( "Train express", "Train express", "Pose de rails",  "TUILE:0");

        checkPlateau(null, List.of(0,1), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, epx, epx1, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_TuileEtoiles_3etoiles() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(66).ajouterRail(joueur);

        Carte epx = new TrainExpress();
        Carte epx1 = new TrainExpress();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, epx, epx1, pr);
        jouerTourPartiel( "Train express", "Train express", "Pose de rails",  "TUILE:75");

        checkPlateau(null, List.of(66, 75), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, epx, epx1, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_TuileEtoiles_2etoiles() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(60).ajouterRail(joueur);

        Carte epx = new TrainExpress();
        Carte epx1 = new TrainExpress();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, epx, epx1, pr);
        jouerTourPartiel( "Train express", "Train express", "Pose de rails",  "TUILE:69");

        checkPlateau(null, List.of(60, 69), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, epx, epx1, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_TuileEtoiles_2etoiles_paspossible() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(60).ajouterRail(joueur);

        Carte c = new TrainOmnibus();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, pr);
        jouerTourPartiel( "Train omnibus", "Pose de rails",  "TUILE:69");

        checkPlateau(null, List.of(60), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c,  pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_TuileEtoiles_3etoiles_paspossible() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(65).ajouterRail(joueur);

        Carte epx = new TrainExpress();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, epx, pr);
        jouerTourPartiel( "Train express", "Pose de rails",  "TUILE:75");

        checkPlateau(null, List.of(65), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, epx, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_TuileEtoiles_4etoiles_paspossible() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(1).ajouterRail(joueur);

        Carte epx = new TrainExpress();
        Carte pr = new PoseDeRails();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, epx, pr);
        jouerTourPartiel( "Train express", "Pose de rails",  "TUILE:0");

        checkPlateau(null, List.of(1), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, epx, pr));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_coopération_avecadv() {
        setupJeu("Coopération");
        initialisation();
        tuiles.get(22).ajouterRail(joueur);
        tuiles.get(23).ajouterRail(joueurs.get(0));

        Carte c = new Cooperation();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, epx);
        jouerTourPartiel("Train express", "Coopération", "TUILE:23");

        checkPlateau(List.of(23), List.of(22, 23), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_sanscoopération_avecadv() {
        setupJeu("Pose de rails");
        initialisation();
        tuiles.get(22).ajouterRail(joueur);
        tuiles.get(23).ajouterRail(joueurs.get(0));

        Carte c = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);
        Carte f2 = reserve.get("Ferraille").get(1);

        addAll(main, c, epx);
        jouerTourPartiel("Train express", "Pose de rails" , "TUILE:23");

        checkPlateau(List.of(23), List.of(22,23), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, epx));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertFalse(containsReference(reserve.get("Ferraille"), f2));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_coopération_sansadv() {
        setupJeu("Coopération");
        initialisation();
        tuiles.get(22).ajouterRail(joueur);

        Carte c = new Cooperation();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        jouerTourPartiel("Coopération",  "TUILE:23");

        checkPlateau(null, List.of(22, 23), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_calcul_fin() {
        setupJeu("Coopération");
        initialisation();
        for (int i = 0; i < 20; i++) {
            tuiles.get(i).ajouterRail(joueur);
            joueur.retirerJetonsRails();
        }
        ((TuileVille) tuiles.get(1)).addGare();
        for (int i = 0; i < 3; i++) {
            ((TuileVille) tuiles.get(8)).addGare();
        }
        for (int i = 0; i < 2; i++) {
            ((TuileVille) tuiles.get(11)).addGare();
        }
        ((TuileVille) tuiles.get(13)).addGare(); //27 points pour les rails

        Carte im = new Immeuble(); //2 points
        Carte gr = new GratteCiel(); //4 points
        Carte ap = new Appartement(); //1 points

        Carte c = new TrainDeTourisme();
        addAll(main, c);
        jouerTourPartiel("Train de tourisme");

        addAll(main, gr);
        addAll(defausse, im);
        addAll(cartesEnJeu, ap);

        assertTrue(containsReferences(main, gr));
        assertTrue(containsReferences(defausse, im));
        assertTrue(containsReferences(cartesEnJeu, ap, c));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertEquals(0, getNbJetonsRails(joueur));
        assertEquals(35, joueur.getScoreTotal());
    }

    @Test
    void test_depotoir_posederails() {
        setupJeu("Dépotoir");
        initialisation();

        Carte c = new Depotoir();
        Carte pr = new PoseDeRails();

        addAll(main, c, pr);

        jouerTourPartiel("Dépotoir", "Pose de rails");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, pr));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_depotoir_poserrails_avec_adv() {
        setupJeu("Dépotoir");
        initialisation();
        tuiles.get(22).ajouterRail(joueur);
        tuiles.get(23).ajouterRail(joueurs.get(0));

        Carte c = new Depotoir();
        Carte pr = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, pr, epx);

        jouerTourPartiel("Train express", "Dépotoir", "Pose de rails", "TUILE:23");

        checkPlateau(List.of(23), List.of(22, 23), null);
        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, pr, epx));
        assertTrue(containsReferences(cartesRecues));
        assertTrue(containsReference(reserve.get("Ferraille"), f));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_depotoir_posederails() {
        setupJeu("Dépotoir", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte de = new Depotoir();
        Carte pr = new PoseDeRails();

        addAll(main, c, pr, de);

        jouerTourPartiel("Bureau du chef de gare", "Dépotoir", "Pose de rails");

        assertTrue(containsReferences(main, de));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, pr));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_depotoir_poserrails_avec_adv() {
        setupJeu("Dépotoir", "Bureau du chef de gare");
        initialisation();
        tuiles.get(22).ajouterRail(joueur);
        tuiles.get(23).ajouterRail(joueurs.get(0));

        Carte c = new BureauDuChefDeGare();
        Carte de = new Depotoir();
        Carte pr = new PoseDeRails();
        Carte epx = new TrainExpress();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c, pr, epx, de);

        jouerTourPartiel("Train express", "Bureau du chef de gare", "Dépotoir", "Pose de rails", "TUILE:23");

        checkPlateau(List.of(23), List.of(22, 23), null);
        assertTrue(containsReferences(main, de));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, pr, epx));
        assertTrue(containsReferences(cartesRecues));
        assertTrue(containsReference(reserve.get("Ferraille"), f));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_decharge() {
        setupJeu("Décharge", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte de = new Decharge();
        Carte fondPioche = new Ferraille();
        Carte f1 = new Ferraille();
        Carte f2 = new Ferraille();
        Carte omni = new TrainOmnibus();

        addAll(main, c, f1, f2, omni, de);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Décharge");

        assertTrue(containsReferences(main, omni, de));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));

        List<Carte> pileFerraille = reserve.get("Ferraille");
        assertTrue(containsReference(pileFerraille, f1));
        assertTrue(containsReference(pileFerraille, f2));
    }

    @Test
    void test_bureau_du_chef_de_gare_depot() {
        setupJeu("Dépôt", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte de = new Depot();
        Carte fondPioche = new Ferraille();
        Carte imm = new Immeuble();
        Carte omni = new TrainOmnibus();
        Carte expr = new TrainExpress();

        addAll(main, c, imm, de);
        addAll(pioche, omni, expr, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Dépôt", "Train express", "Immeuble");

        assertTrue(containsReferences(main, omni, de));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, expr, imm));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_personel_de_gare_piocher() {
        setupJeu("Personnel de gare", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte pe = new PersonnelDeGare();
        Carte fondPioche = new Ferraille();
        Carte ferraille = new Ferraille();
        Carte gare = new Gare();

        addAll(main, c, ferraille, pe);
        addAll(pioche, gare, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Personnel de gare", "piocher");

        assertTrue(containsReferences(main, ferraille, gare, pe));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_personnel_de_gare_argent() {
        setupJeu("Personnel de gare", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte pe = new PersonnelDeGare();
        Carte fondPioche = new Ferraille();
        Carte ferraille = new Ferraille();
        Carte gare = new Gare();

        addAll(main, c, ferraille, pe);
        addAll(pioche, gare, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Personnel de gare", "argent");

        assertTrue(containsReferences(main, ferraille, pe));
        assertTrue(containsReferencesInOrder(pioche, gare, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_personnel_de_gare_ferraille() {
        setupJeu("Personnel de gare", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte pe = new PersonnelDeGare();
        Carte fondPioche = new Ferraille();
        Carte ferraille = new Ferraille();
        Carte gare = new Gare();

        addAll(main, c, ferraille, pe);
        addAll(pioche, gare, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Personnel de gare", "ferraille");

        assertTrue(containsReferences(main, pe));
        assertTrue(containsReferencesInOrder(pioche, gare, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertTrue(containsReference(reserve.get("Ferraille"), ferraille));
    }

    @Test
    void test_bureau_du_chef_de_gare_parc_d_attractions() {
        setupJeu("Parc d'attractions", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte pa = new ParcDAttractions();
        Carte fondPioche = new Ferraille();
        Carte omni = new TrainOmnibus();
        Carte expr = new TrainExpress();

        addAll(main, c, omni, expr, pa);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train omnibus", "Train express", "Bureau du chef de gare", "Parc d'attractions", "Train express");

        assertTrue(containsReferences(main, pa));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni, expr));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(5, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_horaires_estivaux_ecarte() {
        setupJeu("Horaires estivaux", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte he = new HorairesEstivaux();
        Carte fondPioche = new Ferraille();

        addAll(main, c, he);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Horaires estivaux", "oui");

        assertTrue(containsReferences(main, he));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu)); //la carte bureau de chef de gare ne doit pas être ici
        assertTrue(containsReferences(cartesRecues));
        assertEquals(3, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertTrue(containsReferences(cartesEcartees, c)); //elle est ici
    }

    @Test
    void test_bureau_du_chef_de_gare_estivaux_n_ecarte_pas() {
        setupJeu("Horaires estivaux", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte he = new HorairesEstivaux();
        Carte fondPioche = new Ferraille();

        addAll(main, c, he);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Horaires estivaux", "non");

        assertTrue(containsReferences(main, he));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertTrue(containsReferences(cartesEcartees));
    }

    @Test
    void test_bureau_du_chef_de_gare_horaires_temporaires() {
        setupJeu("Horaires temporaires", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte ht = new HorairesTemporaires();
        Carte fondPioche = new Ferraille();
        Carte omni = new TrainOmnibus();
        Carte expr = new TrainExpress();
        Carte gare = new Gare();
        Carte imm = new Immeuble();

        addAll(main, c, ht);
        addAll(pioche, omni, gare, imm, expr, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Horaires temporaires");

        assertTrue(containsReferences(main, omni, expr, ht));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, gare, imm));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_horaires_feu_de_signalisation_defausse() {
        setupJeu("Feu de signalisation", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fs = new FeuDeSignalisation();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte imm = new Immeuble();

        addAll(main, c, fs);
        addAll(pioche, gare, imm, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Feu de signalisation", "oui");

        assertTrue(containsReferences(main, gare, fs));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, imm));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_horaires_feu_de_signalisation_replace() {
        setupJeu("Feu de signalisation", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fs = new FeuDeSignalisation();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte imm = new Immeuble();

        addAll(main, c, fs);
        addAll(pioche, gare, imm, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Feu de signalisation", "non");

        assertTrue(containsReferences(main, gare, fs));
        assertTrue(containsReferencesInOrder(pioche, imm, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_centre_de_controle_perdu() {
        setupJeu("Centre de contrôle", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte cc = new CentreDeControle();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte omni = new TrainOmnibus();

        addAll(main, c, cc);
        addAll(pioche, gare, omni, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Centre de contrôle", "Gare");

        assertTrue(containsReferences(main, gare, cc));
        assertTrue(containsReferencesInOrder(pioche, omni, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_centre_de_controle_gagne() {
        setupJeu("Centre de contrôle", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte cc = new CentreDeControle();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte omni = new TrainOmnibus();

        addAll(main, c, cc);
        addAll(pioche, gare, omni, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Centre de contrôle", "Train omnibus");

        assertTrue(containsReferences(main, gare, omni, cc));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_centre_de_renseignements_prend_carte() {
        setupJeu("Centre de renseignements", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte cr = new CentreDeRenseignements();
        Carte fondPioche = new Ferraille();
        Carte imm = new Immeuble();
        Carte omni = new TrainOmnibus();
        Carte gare = new Gare();
        Carte app = new Appartement();

        addAll(main, c, cr);
        addAll(pioche, imm, omni, gare, app, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Centre de renseignements", "Gare", "Appartement", "Train omnibus", "Immeuble");

        assertTrue(containsReferences(main, gare, cr));
        assertTrue(containsReferencesInOrder(pioche, imm, omni, app, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare_centre_de_renseignements_passe() {
        setupJeu("Centre de renseignements", "Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte cr = new CentreDeRenseignements();
        Carte fondPioche = new Ferraille();
        Carte imm = new Immeuble();
        Carte omni = new TrainOmnibus();
        Carte gare = new Gare();
        Carte app = new Appartement();

        addAll(main, c, cr);
        addAll(pioche, imm, omni, gare, app, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Centre de renseignements", "", "Gare", "Appartement", "Train omnibus", "Immeuble");

        assertTrue(containsReferences(main, cr));
        assertTrue(containsReferencesInOrder(pioche, imm, omni, app, gare, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }



    //Test prof

    @Test
    void test_aiguillage() {
        setupJeu("Aiguillage");
        initialisation();

        Carte c = new Aiguillage();
        Carte fondPioche = new Ferraille();
        Carte gare1 = new Gare();
        Carte gare2 = new Gare();

        addAll(main, c);
        addAll(pioche, gare1, gare2, fondPioche);

        jouerTourPartiel("Aiguillage");

        assertTrue(containsReferences(main, gare1, gare2));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_atelier_de_maintenance() {
        setupJeu("Atelier de maintenance");
        initialisation();

        Carte c = new AtelierDeMaintenance();
        Carte fondPioche = new Ferraille();
        Carte expr1 = new TrainExpress();
        Carte expr2 = reserve.get("Train express").get(0);

        addAll(main, c, expr1);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Atelier de maintenance", "Train express");

        assertTrue(containsReferences(main, expr1));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, expr2));
        assertFalse(containsReference(reserve.get("Train express"), expr2));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_bureau_du_chef_de_gare() {
        setupJeu("Bureau du chef de gare");
        initialisation();

        Carte c = new BureauDuChefDeGare();
        Carte fondPioche = new Ferraille();
        Carte aig = new Aiguillage();
        Carte gare1 = new Gare();
        Carte gare2 = new Gare();

        addAll(main, c, aig);
        addAll(pioche, gare1, gare2, fondPioche);

        jouerTourPartiel("Bureau du chef de gare", "Aiguillage");

        assertTrue(containsReferences(main, aig, gare1, gare2));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_cabine_du_conducteur() {
        setupJeu("Cabine du conducteur");
        initialisation();

        Carte c = new CabineDuConducteur();
        Carte fondPioche = new Ferraille();
        Carte omni1 = new TrainOmnibus();
        Carte omni2 = new TrainOmnibus();
        Carte omni3 = new TrainOmnibus();
        Carte gare1 = new Gare();
        Carte gare2 = new Gare();

        addAll(main, c, omni1, omni2, omni3);
        addAll(pioche, gare1, gare2, fondPioche);

        jouerTourPartiel("Cabine du conducteur", "Train omnibus", "Train omnibus", "");

        assertTrue(containsReferences(main, omni3, gare1, gare2));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, omni1, omni2));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_centre_de_controle_perdu() {
        setupJeu("Centre de contrôle");
        initialisation();

        Carte c = new CentreDeControle();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte omni = new TrainOmnibus();

        addAll(main, c);
        addAll(pioche, gare, omni, fondPioche);

        jouerTourPartiel("Centre de contrôle", "Gare");

        assertTrue(containsReferences(main, gare));
        assertTrue(containsReferencesInOrder(pioche, omni, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_centre_de_controle_gagne() {
        setupJeu("Centre de contrôle");
        initialisation();

        Carte c = new CentreDeControle();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte omni = new TrainOmnibus();

        addAll(main, c);
        addAll(pioche, gare, omni, fondPioche);

        jouerTourPartiel("Centre de contrôle", "Train omnibus");

        assertTrue(containsReferences(main, gare, omni));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_centre_de_renseignements_prend_carte() {
        setupJeu("Centre de renseignements");
        initialisation();

        Carte c = new CentreDeRenseignements();
        Carte fondPioche = new Ferraille();
        Carte imm = new Immeuble();
        Carte omni = new TrainOmnibus();
        Carte gare = new Gare();
        Carte app = new Appartement();

        addAll(main, c);
        addAll(pioche, imm, omni, gare, app, fondPioche);

        jouerTourPartiel("Centre de renseignements", "Gare", "Appartement", "Train omnibus", "Immeuble");

        assertTrue(containsReferences(main, gare));
        assertTrue(containsReferencesInOrder(pioche, imm, omni, app, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_centre_de_renseignements_passe() {
        setupJeu("Centre de renseignements");
        initialisation();

        Carte c = new CentreDeRenseignements();
        Carte fondPioche = new Ferraille();
        Carte imm = new Immeuble();
        Carte omni = new TrainOmnibus();
        Carte gare = new Gare();
        Carte app = new Appartement();

        addAll(main, c);
        addAll(pioche, imm, omni, gare, app, fondPioche);

        jouerTourPartiel("Centre de renseignements", "", "Gare", "Appartement", "Train omnibus", "Immeuble");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, imm, omni, app, gare, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_cooperation_simple() {
        setupJeu("Coopération");
        initialisation();

        Carte c = new Cooperation();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Coopération");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_decharge() {
        setupJeu("Décharge");
        initialisation();

        Carte c = new Decharge();
        Carte fondPioche = new Ferraille();
        Carte f1 = new Ferraille();
        Carte f2 = new Ferraille();
        Carte omni = new TrainOmnibus();

        addAll(main, c, f1, f2, omni);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Décharge");

        assertTrue(containsReferences(main, omni));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));

        List<Carte> pileFerraille = reserve.get("Ferraille");
        assertTrue(containsReference(pileFerraille, f1));
        assertTrue(containsReference(pileFerraille, f2));
    }

    @Test
    void test_depot() {
        setupJeu("Dépôt");
        initialisation();

        Carte c = new Depot();
        Carte fondPioche = new Ferraille();
        Carte imm = new Immeuble();
        Carte omni = new TrainOmnibus();
        Carte expr = new TrainExpress();

        addAll(main, c, imm);
        addAll(pioche, omni, expr, fondPioche);

        jouerTourPartiel("Dépôt", "Train express", "Immeuble");

        assertTrue(containsReferences(main, omni));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, expr, imm));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_depotoir_simple() {
        setupJeu("Dépotoir");
        initialisation();

        Carte c = new Depotoir();
        Carte fondPioche = new Ferraille();

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Dépotoir");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_echangeur() {
        setupJeu("Échangeur");
        initialisation();

        Carte c = new Echangeur();
        Carte fondPioche = new Ferraille();
        Carte omni = new TrainOmnibus();
        Carte expr = new TrainExpress();

        addAll(main, c, omni, expr);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train omnibus", "Train express", "Échangeur", "Train express");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, expr, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(4, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_ferronnerie_simple() {
        setupJeu("Ferronnerie");
        initialisation();

        Carte c = new Ferronnerie();
        Carte fondPioche = new Ferraille();

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Ferronnerie");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_feu_de_signalisation_defausse() {
        setupJeu("Feu de signalisation");
        initialisation();

        Carte c = new FeuDeSignalisation();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte imm = new Immeuble();

        addAll(main, c);
        addAll(pioche, gare, imm, fondPioche);

        jouerTourPartiel("Feu de signalisation", "oui");

        assertTrue(containsReferences(main, gare));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, imm));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_feu_de_signalisation_replace() {
        setupJeu("Feu de signalisation");
        initialisation();

        Carte c = new FeuDeSignalisation();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte imm = new Immeuble();

        addAll(main, c);
        addAll(pioche, gare, imm, fondPioche);

        jouerTourPartiel("Feu de signalisation", "non");

        assertTrue(containsReferences(main, gare));
        assertTrue(containsReferencesInOrder(pioche, imm, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_gare() {
        setupJeu("Gare");
        initialisation();

        Carte c = new Gare();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Gare", "TUILE:12");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertEquals(1, jeu.getTuile(12).getNbGares());
    }

    @Test
    void test_horaires_estivaux_ecarte() {
        setupJeu("Horaires estivaux");
        initialisation();

        Carte c = new HorairesEstivaux();
        Carte fondPioche = new Ferraille();

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Horaires estivaux", "oui");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(3, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertTrue(containsReferences(cartesEcartees, c));
    }

    @Test
    void test_horaires_estivaux_n_ecarte_pas() {
        setupJeu("Horaires estivaux");
        initialisation();

        Carte c = new HorairesEstivaux();
        Carte fondPioche = new Ferraille();

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Horaires estivaux", "non");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertTrue(containsReferences(cartesEcartees));
    }

    @Test
    void test_horaires_temporaires() {
        setupJeu("Horaires temporaires");
        initialisation();

        Carte c = new HorairesTemporaires();
        Carte fondPioche = new Ferraille();
        Carte omni = new TrainOmnibus();
        Carte expr = new TrainExpress();
        Carte gare = new Gare();
        Carte imm = new Immeuble();

        addAll(main, c);
        addAll(pioche, omni, gare, imm, expr, fondPioche);

        jouerTourPartiel("Horaires temporaires");

        assertTrue(containsReferences(main, omni, expr));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, gare, imm));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_parc_d_attractions() {
        setupJeu("Parc d'attractions");
        initialisation();

        Carte c = new ParcDAttractions();
        Carte fondPioche = new Ferraille();
        Carte omni = new TrainOmnibus();
        Carte expr = new TrainExpress();

        addAll(main, c, omni, expr);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train omnibus", "Train express", "Parc d'attractions", "Train express");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni, expr));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(6, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_passage_en_gare() {
        setupJeu("Passage en gare");
        initialisation();

        Carte c = new PassageEnGare();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();

        addAll(main, c);
        addAll(pioche, gare, fondPioche);

        jouerTourPartiel("Passage en gare");

        assertTrue(containsReferences(main, gare));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_personnel_de_gare_piocher() {
        setupJeu("Personnel de gare");
        initialisation();

        Carte c = new PersonnelDeGare();
        Carte fondPioche = new Ferraille();
        Carte ferraille = new Ferraille();
        Carte gare = new Gare();

        addAll(main, c, ferraille);
        addAll(pioche, gare, fondPioche);

        jouerTourPartiel("Personnel de gare", "piocher");

        assertTrue(containsReferences(main, ferraille, gare));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_personnel_de_gare_argent() {
        setupJeu("Personnel de gare");
        initialisation();

        Carte c = new PersonnelDeGare();
        Carte fondPioche = new Ferraille();
        Carte ferraille = new Ferraille();
        Carte gare = new Gare();

        addAll(main, c, ferraille);
        addAll(pioche, gare, fondPioche);

        jouerTourPartiel("Personnel de gare", "argent");

        assertTrue(containsReferences(main, ferraille));
        assertTrue(containsReferencesInOrder(pioche, gare, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_personnel_de_gare_ferraille() {
        setupJeu("Personnel de gare");
        initialisation();

        Carte c = new PersonnelDeGare();
        Carte fondPioche = new Ferraille();
        Carte ferraille = new Ferraille();
        Carte gare = new Gare();

        addAll(main, c, ferraille);
        addAll(pioche, gare, fondPioche);

        jouerTourPartiel("Personnel de gare", "ferraille");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, gare, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertTrue(containsReference(reserve.get("Ferraille"), ferraille));
    }

    @Test
    void test_pont_en_acier_simple() {
        setupJeu("Pont en acier");
        initialisation();

        Carte c = new PontEnAcier();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Pont en acier");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_pose_de_rails() {
        setupJeu("Pose de rails");
        initialisation();

        Carte c = new PoseDeRails();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Pose de rails");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_remorquage() {
        setupJeu("Remorquage");
        initialisation();

        Carte c = new Remorquage();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte omni = new TrainOmnibus();
        Carte expr = new TrainExpress();
        Carte imm = new Immeuble();

        addAll(main, c);
        addAll(pioche, fondPioche);
        addAll(defausse, gare, omni, expr, imm);

        jouerTourPartiel("Remorquage", "Train express");

        assertTrue(containsReferences(main, expr));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, gare, omni, imm));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_salle_de_controle() {
        setupJeu("Salle de contrôle");
        initialisation();

        Carte c = new SalleDeControle();
        Carte fondPioche = new Ferraille();
        Carte gare1 = new Gare();
        Carte gare2 = new Gare();
        Carte gare3 = new Gare();

        addAll(main, c);
        addAll(pioche, gare1, gare2, gare3, fondPioche);

        jouerTourPartiel("Salle de contrôle");

        assertTrue(containsReferences(main, gare1, gare2, gare3));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_tgv_sans_omnibus() {
        setupJeu("TGV");
        initialisation();

        Carte c = new TGV();
        Carte fondPioche = new Ferraille();
        Carte omni = new TrainOmnibus();

        addAll(main, omni, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("TGV", "Train omnibus");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_tgv_avec_omnibus() {
        setupJeu("TGV");
        initialisation();

        Carte c = new TGV();
        Carte fondPioche = new Ferraille();
        Carte omni = new TrainOmnibus();

        addAll(main, omni, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train omnibus", "TGV");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(3, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_tgv_avec_2_omnibus() {
        setupJeu("TGV");
        initialisation();

        Carte c = new TGV();
        Carte fondPioche = new Ferraille();
        Carte omni1 = new TrainOmnibus();
        Carte omni2 = new TrainOmnibus();

        addAll(main, omni1, omni2, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train omnibus", "Train omnibus", "TGV");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, omni1, omni2));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(4, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_train_de_marchandises() {
        setupJeu("Train de marchandises");
        initialisation();

        Carte c = new TrainDeMarchandises();
        Carte fondPioche = new Ferraille();
        Carte f1 = new Ferraille();
        Carte f2 = new Ferraille();
        Carte f3 = new Ferraille();
        int nbFerraille = reserve.get("Ferraille").size();

        addAll(main, c, f1, f2, f3);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train de marchandises", "Ferraille", "Ferraille", "");

        assertTrue(containsSame(main, f3));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(3, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertEquals(reserve.get("Ferraille").size(), nbFerraille + 2);
    }

    @Test
    void test_train_de_tourisme() {
        setupJeu("Train de tourisme");
        initialisation();

        Carte c = new TrainDeTourisme();
        Carte fondPioche = new Ferraille();

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train de tourisme");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertEquals(1, joueur.getScoreTotal());
    }

    @Test
    void test_train_direct() {
        setupJeu("Train direct");
        initialisation();

        Carte c = new TrainDirect();
        Carte fondPioche = new Ferraille();

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train direct");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(3, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_train_express() {
        setupJeu("Train express");
        initialisation();

        Carte c = new TrainExpress();
        Carte fondPioche = new Ferraille();

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train express");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_train_matinal_simple() {
        setupJeu("Train matinal");
        initialisation();

        Carte c = new TrainMatinal();
        Carte fondPioche = new Ferraille();

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train matinal");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_train_matinal_oui() {
        setupJeu("Train matinal");
        initialisation();

        Carte c = new TrainMatinal();
        Carte fondPioche = new Ferraille();
        Carte gare = reserve.get("Gare").get(0);
        Carte expr = new TrainExpress();

        addAll(main, c, expr);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train matinal", "Train express", "Train express", "ACHAT:Gare", "oui");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, gare, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, expr));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertFalse(containsReference(reserve.get("Gare"), gare));
    }

    @Test
    void test_train_matinal_non() {
        setupJeu("Train matinal");
        initialisation();

        Carte c = new TrainMatinal();
        Carte fondPioche = new Ferraille();
        Carte gare = reserve.get("Gare").get(0);
        Carte expr = new TrainExpress();

        addAll(main, c, expr);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train matinal", "Train express", "Train express", "ACHAT:Gare", "non");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c, expr));
        assertTrue(containsReferences(cartesRecues, gare));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertFalse(containsReference(reserve.get("Gare"), gare));
    }

    @Test
    void test_train_omnibus() {
        setupJeu("Train omnibus");
        initialisation();

        Carte c = new TrainOmnibus();
        Carte fondPioche = new Ferraille();

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train omnibus");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(1, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_train_postal() {
        setupJeu("Train postal");
        initialisation();

        Carte c = new TrainPostal();
        Carte fondPioche = new Ferraille();
        Carte gare = new Gare();
        Carte imm = new Immeuble();

        addAll(main, c, imm, gare);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train postal", "Immeuble", "");

        assertTrue(containsReferences(main, gare));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, imm));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_tunnel_simple() {
        setupJeu("Tunnel");
        initialisation();

        Carte c = new Tunnel();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Tunnel");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_usine_de_wagons() {
        setupJeu("Usine de wagons");
        initialisation();

        Carte c = new UsineDeWagons();
        Carte fondPioche = new Ferraille();
        Carte omni = new TrainOmnibus();
        Carte expr = new TrainExpress();
        Carte direct = reserve.get("Train direct").get(0);

        addAll(main, c, omni, expr);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Usine de wagons", "Train express", "ACHAT:Train direct");

        assertTrue(containsReferences(main, omni, direct));
        assertFalse(reserve.get("Train direct").contains(direct));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
        assertTrue(containsReferences(cartesEcartees, expr));
    }

    @Test
    void test_viaduc_simple() {
        setupJeu("Viaduc");
        initialisation();

        Carte c = new Viaduc();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Viaduc");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    @Test
    void test_voie_souterraine_simple() {
        setupJeu("Voie souterraine");
        initialisation();

        Carte c = new VoieSouterraine();
        Carte fondPioche = new Ferraille();
        Carte f = reserve.get("Ferraille").get(0);

        addAll(main, c);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Voie souterraine");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, c));
        assertTrue(containsReferences(cartesRecues, f));
        assertFalse(containsReference(reserve.get("Ferraille"), f));
        assertEquals(0, getArgent(joueur));
        assertEquals(1, getPointsRails(joueur));
    }

    private void testAcheterCarte(String nomCarte, int cout) {
        setupJeu(nomCarte);
        initialisation();

        Carte c = reserve.get(nomCarte).get(0);

        List<Carte> omnibus = new ArrayList<>();
        for (int i = 0; i < cout + 1; i++) {
            omnibus.add(new TrainOmnibus());
        }
        main.addAll(omnibus);

        List<String> instructions = new ArrayList<>();
        for (int i = 0; i < cout + 1; i++) {
            instructions.add("Train omnibus");
        }
        instructions.add("ACHAT:" + nomCarte);

        jouerTourPartiel(instructions);

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, omnibus));
        assertTrue(containsReferences(cartesRecues, c));
        assertEquals(getArgent(joueur), 1);
        assertEquals(c.getNom(), nomCarte);
    }

    @Test
    void test_acheter_carte() {
        testAcheterCarte("Aiguillage", 5);
        testAcheterCarte("Atelier de maintenance", 5);
        testAcheterCarte("Bureau du chef de gare", 4);
        testAcheterCarte("Cabine du conducteur", 2);
        testAcheterCarte("Centre de contrôle", 3);
        testAcheterCarte("Centre de renseignements", 4);
        testAcheterCarte("Coopération", 5);
        testAcheterCarte("Décharge", 2);
        testAcheterCarte("Dépôt", 3);
        testAcheterCarte("Dépotoir", 5);
        testAcheterCarte("Échangeur", 3);
        testAcheterCarte("Ferronnerie", 4);
        testAcheterCarte("Feu de signalisation", 2);
        testAcheterCarte("Gare", 3);
        testAcheterCarte("Horaires estivaux", 3);
        testAcheterCarte("Horaires temporaires", 5);
        testAcheterCarte("Parc d'attractions", 4);
        testAcheterCarte("Passage en gare", 3);
        testAcheterCarte("Personnel de gare", 2);
        testAcheterCarte("Pont en acier", 4);
        testAcheterCarte("Pose de rails", 3);
        testAcheterCarte("Remorquage", 3);
        testAcheterCarte("Salle de contrôle", 7);
        testAcheterCarte("TGV", 2);
        testAcheterCarte("Train de marchandises", 4);
        testAcheterCarte("Train de tourisme", 4);
        testAcheterCarte("Train direct", 6);
        testAcheterCarte("Train express", 3);
        testAcheterCarte("Train matinal", 5);
        testAcheterCarte("Train postal", 4);
        testAcheterCarte("Tunnel", 5);
        testAcheterCarte("Usine de wagons", 5);
        testAcheterCarte("Viaduc", 5);
        testAcheterCarte("Voie souterraine", 7);
    }

    @Test
    void test_debut_jeu() {
        setupJeu();

        // Le joueur 1 choisit de placer son premier rail sur la tuile 14 puis le joueur
        // 2 choisit de placer son premier rail sur la tuile 62
        jeu.setInput("TUILE:14", "TUILE:62");

        try {
            jeu.run();
        } catch (Exception e) {
            assertEquals("fr.umontpellier.iut.trains.Joueur.jouerTour", getMethodeQuiAttendInput(e));
        }
        checkPlateau(List.of(14), List.of(62), null);
    }

    @Test
    void test_jouer_tour_passer() {
        setupJeu();
        initialisation();

        Carte omni1 = new TrainOmnibus();
        Carte omni2 = new TrainOmnibus();
        Carte omni3 = new TrainOmnibus();
        Carte omni4 = new TrainOmnibus();
        Carte omni5 = new TrainOmnibus();
        Carte gare1 = new Gare();
        Carte gare2 = new Gare();
        Carte gare3 = new Gare();
        Carte gare4 = new Gare();
        Carte gare5 = new Gare();
        Carte fondPioche = new Ferraille();

        addAll(main, omni1, omni2, omni3, omni4, omni5);
        addAll(pioche, gare1, gare2, gare3, gare4, gare5, fondPioche);

        jeu.setInput("");
        joueur.jouerTour();

        assertTrue(containsReferences(main, gare1, gare2, gare3, gare4, gare5));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse, omni1, omni2, omni3, omni4, omni5));
        assertTrue(containsReferences(cartesEnJeu));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_jouer_tour_acheter_gare_pas_assez_d_argent() {
        setupJeu("Dépotoir");
        initialisation();

        Carte direct = new TrainDirect();
        Carte express = new TrainExpress();
        Carte fondPioche = new Ferraille();

        addAll(main, direct, express);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train express", "ACHAT:Gare");

        assertTrue(containsReferences(main, direct));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, express));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_jouer_tour_acheter_gare() {
        setupJeu();
        initialisation();

        Carte direct = new TrainDirect();
        Carte express = new TrainExpress();
        Carte fondPioche = new Ferraille();
        Carte gare = reserve.get("Gare").get(0);
        addAll(main, direct, express);
        addAll(pioche, fondPioche);

        jouerTourPartiel("Train direct", "Train express", "ACHAT:Gare");

        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu, direct, express));
        assertTrue(containsReferences(cartesRecues, gare));
        assertEquals(2, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_ajouter_rail_plaine() {
        setupJeu();
        initialisation();
        tuiles.get(22).ajouterRail(joueur);

        Carte pose = new PoseDeRails();
        addAll(main, pose);

        jouerTourPartiel("Pose de rails", "TUILE:23");

        checkPlateau(null, List.of(22, 23), null);
    }

    @Test
    void test_ajouter_rail_fleuve_pas_assez_d_argent() {
        setupJeu();
        initialisation();
        tuiles.get(22).ajouterRail(joueur);

        Carte pose = new PoseDeRails();
        addAll(main, pose);

        jouerTourPartiel("Pose de rails", "TUILE:21");

        checkPlateau(null, List.of(22), null);
    }

    @Test
    void test_ajouter_rail_fleuve() {
        setupJeu();
        initialisation();
        tuiles.get(22).ajouterRail(joueur);

        Carte pose = new PoseDeRails();
        Carte omni = new TrainOmnibus();
        addAll(main, pose, omni);

        jouerTourPartiel("Pose de rails", "Train omnibus", "TUILE:21");

        checkPlateau(null, List.of(22, 21), null);
    }

    @Test
    void test_piocher() {
        setupJeu();
        initialisation();

        Carte omni = new TrainOmnibus();
        Carte gare = new Gare();
        Carte fondPioche = new Ferraille();

        addAll(pioche, omni, gare, fondPioche);

        Carte c = joueur.piocher();

        assertTrue(c == omni);
        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, gare, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_piocher_deux_cartes() {
        setupJeu();
        initialisation();

        Carte omni = new TrainOmnibus();
        Carte gare = new Gare();
        Carte fondPioche = new Ferraille();

        addAll(pioche, omni, gare, fondPioche);

        List<Carte> cartes = joueur.piocher(2);

        assertTrue(containsReferences(cartes, omni, gare));
        assertTrue(containsReferences(main));
        assertTrue(containsReferencesInOrder(pioche, fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_piocher_deux_cartes_avec_melange() {
        setupJeu();
        initialisation();

        Carte omni = new TrainOmnibus();
        Carte gare1 = new Gare();
        Carte gare2 = new Gare();

        addAll(pioche, omni);
        addAll(defausse, gare1, gare2);

        List<Carte> cartes = joueur.piocher(2);

        assertTrue(containsSame(cartes, omni, gare1));
        assertTrue(containsReferences(main));
        assertTrue(containsSame(pioche, gare2));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getArgent(joueur));
        assertEquals(0, getPointsRails(joueur));
    }

    @Test
    void test_centreDeRenseignement_remettrePioche_carteChoisie()
    {
        setupJeu();
        initialisation();

        Carte c = new CentreDeRenseignements();

        Carte omni1 = new TrainOmnibus();
        Carte pdg = new PersonnelDeGare();
        Carte v = new Viaduc();
        Carte omni2 = new TrainOmnibus();
        Carte fondPioche = new Ferraille();

        addAll(pioche, omni1, pdg, v, omni2, fondPioche);
        addAll(main,c);

        jouerTourPartiel("Centre de renseignements", "Personnel de gare", "Personnel de gare", "Viaduc", "Train omnibus", "Train omnibus");

        assertTrue(containsReferences(main,pdg));
        assertTrue(containsReferencesInOrder(pioche,omni2,omni1,v,fondPioche));
        assertTrue(containsReferences(defausse));
        assertTrue(containsReferences(cartesEnJeu,c));
        assertTrue(containsReferences(cartesRecues));
        assertEquals(0, getPointsRails(joueur));
        assertEquals(1, getArgent(joueur));
    }

}