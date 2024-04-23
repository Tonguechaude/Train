package fr.umontpellier.iut.trains;

import java.util.*;

import fr.umontpellier.iut.trains.cartes.*;
import fr.umontpellier.iut.trains.plateau.Tuile;
import fr.umontpellier.iut.trains.plateau.TuileVille;
import fr.umontpellier.iut.trains.plateau.TypeTerrain;

public class Joueur {
    /**
     * Le jeu auquel le joueur est rattaché
     */
    private Jeu jeu;
    /**
     * Nom du joueur (pour les affichages console et UI)
     */
    private String nom;
    /**
     * Quantité d'argent que le joueur a (remis à zéro entre les tours)
     */
    private int argent;
    /**
     * Nombre de points rails dont le joueur dispose. Ces points sont obtenus en
     * jouant les cartes RAIL (vertes) et remis à zéro entre les tous
     */
    private int pointsRails;
    /**
     * Nombre de jetons rails disponibles (non placés sur le plateau)
     */
    private int nbJetonsRails;
    /**
     * Score courant de points de victoires obtenus durant la partie.
     * <> score total du joueur. (cf. getScoreTotal();)
     */
    private int scoreCourant;
    /**
     * Liste des réductions que possède le joueur pour poser des rails
     */
    private HashSet<TypeTerrain> listReductions;
    /**
     * Liste des cartes en main
     */
    private ListeDeCartes main;
    /**
     * Liste des cartes dans la pioche (le début de la liste correspond au haut de
     * la pile)
     */
    private ListeDeCartes pioche;
    /**
     * Liste de cartes dans la défausse
     */
    private ListeDeCartes defausse;
    /**
     * Liste des cartes en jeu (cartes jouées par le joueur pendant le tour)
     */
    private ListeDeCartes cartesEnJeu;
    /**
     * Liste des cartes reçues pendant le tour
     */
    private ListeDeCartes cartesRecues;
    /**
     * Couleur du joueur (utilisé par l'interface graphique)
     */
    private CouleurJoueur couleur;

    public Joueur(Jeu jeu, String nom, CouleurJoueur couleur) {
        this.jeu = jeu;
        this.nom = nom;
        this.couleur = couleur;
        argent = 0;
        pointsRails = 0;
        nbJetonsRails = 20;
        scoreCourant = 0;
        main = new ListeDeCartes();
        defausse = new ListeDeCartes();
        pioche = new ListeDeCartes();
        cartesEnJeu = new ListeDeCartes();
        cartesRecues = new ListeDeCartes();
        listReductions = new HashSet<>();

        // créer 7 Train omnibus (non disponibles dans la réserve)
        pioche.addAll(FabriqueListeDeCartes.creerListeDeCartes("Train omnibus", 7));
        // prendre 2 Pose de rails de la réserve
        for (int i = 0; i < 2; i++) {
            pioche.add(jeu.prendreDansLaReserve("Pose de rails"));
        }
        // prendre 1 Gare de la réserve
        pioche.add(jeu.prendreDansLaReserve("Gare"));

        // mélanger la pioche
        pioche.melanger();
        // Piocher 5 cartes en main
        setMain(piocher(5));

    }

    public String getNom() {
        return nom;
    }

    public CouleurJoueur getCouleur() {
        return couleur;
    }
    public int getNbJetonsRails() {return nbJetonsRails;}

    public ListeDeCartes getCartesEnJeu() {
        return cartesEnJeu;
    }


    public void setCartesEnJeu(ListeDeCartes cartesEnJeu) {
        this.cartesEnJeu = cartesEnJeu;
    }

    /**
     * Renvoie le score total du joueur
     * <p>
     * Le score total est la somme des points obtenus par les effets suivants :
     * <ul>
     * <li>points de rails (villes et lieux éloignés sur lesquels le joueur a posé
     * un rail)
     * <li>points des cartes possédées par le joueur (cartes VICTOIRE jaunes)
     * <li>score courant du joueur (points marqués en jouant des cartes pendant la
     * partie p.ex. Train de tourisme)
     * </ul>
     * 
     * @return le score total du joueur
     */
    public int getScoreTotal() {
        int scoreTotal = scoreCourant;
        for(Tuile tuile : jeu.getTuiles()) {
            scoreTotal += tuile.getPointVictoire(this);
        }

        for(Carte carte : main)
        {
            scoreTotal += carte.getPointVictoire();
        }

        for(Carte carte : cartesEnJeu)
        {
            scoreTotal += carte.getPointVictoire();
        }

        for(Carte carte : pioche)
        {
            scoreTotal += carte.getPointVictoire();
        }

        return scoreTotal;
    }

    /**
     * Retire et renvoie la première carte de la pioche.
     * <p>
     * Si la pioche est vide, la méthode commence par mélanger toute la défausse
     * dans la pioche.
     *
     * @return la carte piochée ou {@code null} si aucune carte disponible
     */
    public Carte piocher() {
        if(pioche.isEmpty())
        {
            if(defausse.isEmpty()) {
                return null;
            }
            pioche.addAll(defausse);
            pioche.melanger();
            defausse.clear();
        }

        Carte piocher = pioche.remove(0);

        return piocher;
    }

    public void remettreAuDessusPioche (Carte carte)
    {
        pioche.add(0,carte);
    }

    /**
     * Retire et renvoie les {@code n} premières cartes de la pioche.
     * <p>
     * Si à un moment il faut encore piocher des cartes et que la pioche est vide,
     * la défausse est mélangée et toutes ses cartes sont déplacées dans la pioche.
     * S'il n'y a plus de cartes à piocher la méthode s'interromp et les cartes qui
     * ont pu être piochées sont renvoyées.
     * 
     * @param n nombre de cartes à piocher
     * @return une liste des cartes piochées (la liste peut contenir moins de n
     *         éléments si pas assez de cartes disponibles dans la pioche et la
     *         défausse)
     */
    public List<Carte> piocher(int n) {
        List<Carte> list = new ArrayList<>();
            for(int i = 1 ; i <= n ; i++)
            {
                Carte C = piocher();
                if(C != null)
                {
                    list.add(C);
                } else
                {
                    return list;
                }
            }
        return list;
    }

    /**
     * Joue un tour complet du joueur
     * <p>
     * Le tour du joueur se déroule en plusieurs étapes :
     * <ol>
     * <li>Initialisation
     * <p>
     * Dans ce jeu il n'y a rien de particulier à faire en début de tour à part un
     * éventuel affichage dans le log.
     * 
     * <li>Boucle principale
     * <p>
     * C'est le cœur de la fonction. Tant que le tour du joueur n'est pas terminé,
     * il faut préparer la liste de tous les choix valides que le joueur peut faire
     * (jouer des cartes, poser des rails, acheter des cartes, etc.), puis demander
     * au joueur de choisir une action (en appelant la méthode {@code choisir}).
     * <p>
     * Ensuite, en fonction du choix du joueur il faut exécuter l'action demandée et
     * recommencer la boucle si le tour n'est pas terminé.
     * <p>
     * Le tour se termine lorsque le joueur décide de passer (il choisit {@code ""})
     * ou lorsqu'il exécute une action qui termine automatiquement le tour (par
     * exemple s'il choisit de recycler toutes ses cartes Ferraille en début de
     * tour)
     * 
     * <li>Finalisation
     * <p>
     * Actions à exécuter à la fin du tour : réinitialiser les attributs
     * du joueur qui sont spécifiques au tour (argent, rails, etc.), défausser
     * toutes les
     * cartes, piocher 5 nouvelles cartes en main, etc.
     * </ol>
     */
    public void jouerTour()
    {
        // Initialisation
        jeu.log("<div class=\"tour\">Tour de " + toLog() + "</div>");
        boolean passerTour = true; //
        boolean finTour = false;
        // Boucle principale
        while (!finTour) {
            List<String> choixPossibles = new ArrayList<>();
            for (Carte c : main) {
                // ajoute les noms de toutes les cartes en main
                choixPossibles.add(c.getNom());
            }
            for (String nomCarte : jeu.getReserve().keySet()) {
                // ajoute les noms des cartes dans la réserve préfixés de "ACHAT:"
                choixPossibles.add("ACHAT:" + nomCarte);
            }

            for (int i = 0; i < jeu.getTuiles().size(); i++) {
                // ajoute les indexes des tuiles dans les choix possibles pour la pose de RAILS (non de gare)
                    choixPossibles.add("TUILE:" + i);
            }
            // Choix de l'action à réaliser
            String choix = choisir(String.format("Tour de %s", this.nom), choixPossibles, null, true);

            if (choix.startsWith("ACHAT:")) {
                // prendre une carte dans la réserve
                String nomCarte = choix.split(":")[1];
                Carte carte = jeu.prendreDansLaReserve(nomCarte);
                if (carte != null) {
                    if(isRichEnough(carte.getPrix())) //si le joueur a assez d'argent pour acheter la carte
                    {
                        if (cartesEnJeu.getCarte("Train matinal") != null) //gère le train matinal
                        {
                            trainMatinal(carte);
                        }
                        else if (carte.getType().equals("Victoire"))
                        {
                            log("Reçoit " + carte); // affichage dans le log
                            cartesRecues.add(carte);
                            addFerraille(1);
                        }
                        else {
                            log("Reçoit " + carte); // affichage dans le log
                            cartesRecues.add(carte);
                        }
                        addArgent(-carte.getPrix());
                    } else {
                        log(String.format("%s ne possède pas assez d'argent",nom));
                    }
                }
            } else if (choix.startsWith("TUILE:"))
            {
                if(pointsRails > 0)
                {
                    String indexTuile = choix.split(":")[1];
                    Tuile tuile = jeu.getTuile(Integer.parseInt(indexTuile));
                        poseDeRail(tuile);
                } else {
                    log(String.format("%s n'a pas assez de points pour poser un rail",this.nom));
                }

            }
            else if (choix.equals(""))
            {
                // action spéciale pour défausser la ferraille de la main si l'on passe directement notre tour !!
                if(passerTour)
                {
                    List<Bouton> ouiOUnon = Arrays.asList(
                        new Bouton("oui"),
                        new Bouton("non")
                    );
                    String deffausserFerraille = choisir("voulez vous défausser la feraille de votre main ?",null,ouiOUnon,false);
                    if(deffausserFerraille.equals("oui"))
                    {
                        removeFerraille(-1); // -1 permet de retirer toute la ferraille de la main
                    }
                }
                // terminer le tour
                finTour = true;
            }
            else
            {
                // jouer une carte de la main
                Carte carte = main.retirer(choix);
                if(carte instanceof CarteJaune) {
                    log("Vous ne pouvez pas jouer une carte <Victoire>");
                    main.add(carte);
                } else {
                    this.addArgent(carte.getValeur()); // le joueur gagne la valeur de la carte à la posée
                    log("Joue " + carte); // affichage dans le log
                    cartesEnJeu.add(carte); // mettre la carte en jeu
                    carte.jouer(this); // exécuter l'action de la carte
                }
            }
            passerTour = false;
        }
        // Finalisation
        listReductions.clear(); //réductions ne durent qu'un tour
        argent = 0; // l'argent est réinitialisé chaque tour

        // défausser toutes les cartes
        defausse.addAll(main);
        main.clear();
        defausse.addAll(cartesRecues);
        cartesRecues.clear();
        defausse.addAll(cartesEnJeu);
        cartesEnJeu.clear();

        setMain(piocher(5)); // piocher 5 cartes en main
    }

    /**
     * Attend une entrée de la part du joueur (au clavier ou sur la websocket) et
     * renvoie le choix du joueur.
     * <p>
     * Cette méthode lit les entrées du jeu ({@code Jeu.lireligne()}) jusqu'à ce
     * qu'un choix valide (un élément de {@code choix} ou la valeur d'un élément de
     * {@code boutons} ou éventuellement la chaîne vide si l'utilisateur est
     * autorisé à passer) soit reçu.
     * Lorsqu'un choix valide est obtenu, il est renvoyé par la fonction.
     * <p>
     * Exemple d'utilisation pour demander à un joueur de répondre à une question
     * par "oui" ou "non" :
     * <p>
     * 
     * <pre>{@code
     * List<String> choix = Arrays.asList("oui", "non");
     * String input = choisir("Voulez-vous faire ceci ?", choix, null, false);
     * }</pre>
     * <p>
     * Si par contre on voulait proposer les réponses à l'aide de boutons, on
     * pourrait utiliser :
     * 
     * <pre>{@code
     * List<Bouton> boutons = Arrays.asList(new Bouton("Oui !", "oui"), new Bouton("Non !", "non"));
     * String input = choisir("Voulez-vous faire ceci ?", null, boutons, false);
     * }</pre>
     * 
     * (ici le premier bouton a le label "Oui !" et envoie la String "oui" s'il est
     * cliqué, le second a le label "Non !" et envoie la String "non" lorsqu'il est
     * cliqué)
     *
     * <p>
     * <b>Remarque :</b> Normalement, si le paramètre {@code peutPasser} est
     * {@code false} le choix
     * {@code ""} n'est pas valide. Cependant s'il n'y a aucun choix proposé (les
     * listes {@code choix} et {@code boutons} sont vides ou {@code null}), le choix
     * {@code ""} est accepté pour éviter un blocage.
     * 
     * @param instruction message à afficher à l'écran pour indiquer au joueur la
     *                    nature du choix qui est attendu
     * @param choix       une collection de chaînes de caractères correspondant aux
     *                    choix valides attendus du joueur (ou {@code null})
     * @param boutons     une liste d'objets de type {@code Bouton} définis par deux
     *                    chaînes de caractères (label, valeur) correspondant aux
     *                    choix valides attendus du joueur qui doivent être
     *                    représentés par des boutons sur l'interface graphique (le
     *                    label est affiché sur le bouton, la valeur est ce qui est
     *                    envoyé au jeu quand le bouton est cliqué) ou {@code null}
     * @param peutPasser  booléen indiquant si le joueur a le droit de passer sans
     *                    faire de choix. S'il est autorisé à passer, c'est la
     *                    chaîne de caractères vide ({@code ""}) qui signifie qu'il
     *                    désire passer.
     * @return le choix de l'utilisateur (un élement de {@code choix}, ou la valeur
     *         d'un élément de {@code boutons} ou la chaîne vide)
     */
    public String choisir(
            String instruction,
            Collection<String> choix,
            List<Bouton> boutons,
            boolean peutPasser) {
        if (choix == null)
            choix = new ArrayList<>();
        if (boutons == null)
            boutons = new ArrayList<>();

        HashSet<String> choixDistincts = new HashSet<>(choix);
        choixDistincts.addAll(boutons.stream().map(Bouton::valeur).toList());
        if (peutPasser || choixDistincts.isEmpty()) {
            // si le joueur a le droit de passer ou s'il n'existe aucun choix valide, on
            // ajoute "" à la liste des choix possibles
            choixDistincts.add("");
        }

        String entree;
        // Lit l'entrée de l'utilisateur jusqu'à obtenir un choix valide
        while (true) {
            jeu.prompt(instruction, boutons, peutPasser);
            entree = jeu.lireLigne();
            // si une réponse valide est obtenue, elle est renvoyée
            if (choixDistincts.contains(entree)) {
                return entree;
            }
        }
    }

    /**
     * Ajoute un message dans le log du jeu
     * 
     * @param message message à ajouter dans le log
     */
    public void log(String message) {
        jeu.log(message);
    }

    @Override
    public String toString() {
        // Vous pouvez modifier cette fonction comme bon vous semble pour afficher
        // d'autres informations si nécessaire
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(String.format("=== %s (%d pts) ===", nom, getScoreTotal()));
        joiner.add(String.format("  Argent: %d  Rails: %d", argent, pointsRails));
        joiner.add("  Cartes en jeu: " + cartesEnJeu);
        joiner.add("  Cartes reçues: " + cartesRecues);
        joiner.add("  Cartes en main: " + main);
        return joiner.toString();
    }

    /**
     * @return une représentation du joueur pour l'affichage dans le log du jeu
     */
    public String toLog() {
        return String.format("<span class=\"joueur %s\">%s</span>", couleur.toString(), nom);
    }

    /**
     * @return une représentation du joueur sous la forme d'un dictionnaire de
     *         valeurs sérialisables (qui sera converti en JSON pour l'envoyer à
     *         l'interface graphique)
     */
    Map<String, Object> dataMap() {
        return Map.ofEntries(
                Map.entry("nom", nom),
                Map.entry("couleur", couleur),
                Map.entry("scoreTotal", getScoreTotal()),
                Map.entry("argent", argent),
                Map.entry("rails", pointsRails),
                Map.entry("nbJetonsRails", nbJetonsRails),
                Map.entry("main", main.dataMap()),
                Map.entry("defausse", defausse.dataMap()),
                Map.entry("cartesEnJeu", cartesEnJeu.dataMap()),
                Map.entry("cartesRecues", cartesRecues.dataMap()),
                Map.entry("pioche", pioche.dataMap()),
                Map.entry("actif", jeu.getJoueurCourant() == this));
    }

    public int getArgent() {
        return argent;
    }

    public int getPointsRails() {
        return pointsRails;
    }

    public void addPtVictoire(int score) {
        scoreCourant += score;
    }
    public void addArgent(int argent) {
        this.argent += argent;
    }
    public void addPointDeRails(int nb) {
        this.pointsRails += nb;
    }
    public void addCarteRecue (Carte carte)
    {
        cartesRecues.add(carte);
    }
    public void addReduction(TypeTerrain reduction) {
        listReductions.add(reduction);
    }

    public HashSet<TypeTerrain> getListReductions() {
        return listReductions;
    }

    public ListeDeCartes getMain() {
        return main;
    }

    public Jeu getJeu() {
        return jeu;
    }

    public ListeDeCartes getDefausse() {
        return defausse;
    }

    public ListeDeCartes getPioche() {
        return pioche;
    }

    /**
     * set la main du joueur en y ajoutant le set de cartes passé en paramètres
     * @param main contient les cartes à ajouter à la main du joueur
     */
    public void setMain(List<Carte> main ) {
        this.main.addAll(main);
    }

    /**
     * Récupère une liste du nom des cartes en main
     * @return une @ArrayList<String> des noms de carte présent dans la main du joueur
     */
    public List<String> getNomMain ()
    {
        List<String> liste = new ArrayList<>();
        for ( Carte c : main)
        {
            liste.add(c.getNom());
        }
        return liste;
    }

    /**
     * retire nbFerraille de la pile (réserve) pour l'ajouter dans les cartes reçues du joueur
     *
     * @param nbFerraile représente le nombre de cartes ferraille à manipuler
     */
    public void addFerraille(int nbFerraile) {
        for(int i = 0 ; i < nbFerraile; i++) {
            if(jeu.getReserve().get("Ferraille").isEmpty()){
                break;
            } else{
                cartesRecues.add(jeu.getReserve().get("Ferraille").retirer("Ferraille"));
            }
        }
    }

    /**
     * enlève nbFerraille de la main du joueur pour les remettre dans la pile
     * si nbFerraille == -1 --> retire toute la ferraille presente dans la main
     *
     * @param nbFerraille représente le nombre de Ferraille a enlever de la main du joueur et a reposer dans la pile (réserve)
     */
    public void removeFerraille(int nbFerraille){

        //récupérer la liste des cartes dans la main pour vérifier la présence de ferraille.
        while(this.getNomMain().contains("Ferraille") && (nbFerraille != 0))
        {
            //remet la feraille retirée dans la pile de la réserve (correspondant à la pile de Ferraille).
            jeu.getReserve().get("Ferraille").add(main.retirer("Ferraille"));

            nbFerraille--;
        }
    }

    /**
     * ajoute {@param carte} à la main du joueur.
     *
     * @param carte i.e la carte à ajouter dans la main du joueur.
     */
    public void ajouterCarteMain(Carte carte) {
        main.add(carte);
    }

    public void retirerCarteMain (Carte carte) {
        main.remove(carte);
    }

    public void ajouterCarteReçue(Carte carte) {
        cartesRecues.add(carte);
    }



    /**
     * gère la pose de rail selon la tuile passée en paramètre.
     * prends en compte:
     * <ul>
     * <li> l'argent du joueur
     * <li> les rails posés dessus
     * <li> les réductions appliquées au joueur
     * <li> la feraille ajoutée au joueur en cas de joueur présent sur la tuile
     *</ul>
     *
     * @param tuile i.e la tuile sur laquelle poser le rail
     */
    public void poseDeRail(Tuile tuile) {
        if(tuile.peutPoserRail(this))
        {
                nbJetonsRails--;
                if(!tuile.estVide())
                {
                    this.addFerraille(1);
                }
            // {-} douteux, si bug, à vérifier
            addArgent(-tuile.surcoutPoseDeRail(this));
            tuile.ajouterRail(this);
        }
    }

    /**
     * gère l'action du train matinal.
     * @param carte la carte achetée à décider si on la place dans la pioche ou dans les cartes reçues.
     */
    public void trainMatinal(Carte carte) {
        String instructions = "voulez vous l'ajouter sur le dessus de votre pioche ?";
        List<Bouton> ouiOUnon = Arrays.asList(new Bouton("oui", "oui"), new Bouton("non", "non"));
        String reponse = choisir(instructions, null, ouiOUnon, false);

        log("Reçoit " + carte); // affichage dans le log
        switch (reponse)
        {
            case "oui":
                pioche.add(0,carte);
                break;
            case "non":
                cartesRecues.add(carte);
                break;
        }
    }

    /**
     * vérifie si le joueur possède une somme d'argent supérieure ou égale à n
     *
     * @param n représente l'argent à comparer par rapport à celui du joueur
     * @return true si le joueur à assez d'argent, false sinon
     */
    public boolean isRichEnough(int n) {
        if(argent < n) {
            getJeu().log(String.format("%s ne possède pas assez d'argent",nom));
        }
        return argent >= n;
    }

    public void ajouterCarteDefausse (Carte carte)
    {
        defausse.add(carte);
    }

    public ListeDeCartes getCartesRecues() {
        return cartesRecues;
    }

    /**
     * Defausse une carte passée en paramètre en la retirant de la main et en l'ajoutant aux cartes Écartées
     * @param carte
     */
    public void defausserCarte(Carte carte)
    {
            defausse.add(carte);
            main.remove(carte);
            jeu.getCartesEcartees().add(carte);
    }

    /**
     * Prends une list de carte et renvoi une list contenant leur noms
     * @param cartes
     * @return
     */
    public List<String> getNomsCartes(List<Carte> cartes) {
        List<String> nomsCartes = new ArrayList<>();
        for (Carte carte : cartes) {
            nomsCartes.add(carte.getNom());
        }
        return nomsCartes;
    }

    public void addDefausse (Carte carte)
    {
        defausse.add(carte);
    }
}
