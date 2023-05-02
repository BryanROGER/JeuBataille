import java.io.IOException;
import java.util.*;

public class DeroulementPartie {

    DeckDeCartes deckDeJeu;
    Joueur joueur1 = new Joueur("Joueur 1");
    Joueur joueur2 = new Joueur("Joueur 2");
    boolean continuerPartie = true;
    byte continuer;
    byte typeDAvancement;

    // Constructeur de la classe Deroulement partie créant un nouveau deck, lors de la création les cartes sont déjà mélangées
    public DeroulementPartie() throws IOException {

        deckDeJeu = new DeckDeCartes();


    }



    // La méthode distribuer permet de donner les cartes impaires à un joueur et les cartes paires à un autre, permettant ainsi de répartir le deckDeCarte en deux decks joueurs égaux
    public void distribuer() {
        for (int i = 0; i < deckDeJeu.sizeDeck(); i++) {
            UneCarte temp = deckDeJeu.getCarte(i);
            if (i % 2 == 0) {
                joueur1.cartesDuJoueur.add(temp);
            } else {
                joueur2.cartesDuJoueur.add(temp);
            }
        }
    }

    //Méthode permettant à chaque joueur de transférer la carte à l'indice 0 des cartesDuJoueur à terrainJoueur
    //la carte indice 0 est alors supprimée dans la liste cartesDuJoueur
    public void jouerCarte(Joueur j1, Joueur j2) {
        j1.jouerCarte();
        j2.jouerCarte();
    }


    /*
    * Descriptif de la méthode manche
    *   faire une boucle tant que la variable continuerPartie = true
    *   La variable passe à false quand le nombre total de cartes d'un joueur en fin de tour est de 0
    *   cette modification est faite dans la méthode "verificationJoueurs()"
    *
    *
    * */
    public void manche() throws IOException {
        System.out.println("\n\n\n+---------------------------------------------------------------------------------------------------------------------+\n");
        int valeur;
        int j = 1;
        Scanner lectureClavier = new Scanner(System.in);
        do  {
            int i = 1;

            // Boucle while permettant de mettre fin au tour, c'est-à-dire quand un des joueurs n'a plus de cartes non jouées
            while (joueur1.cartesDuJoueur.size() > 0 && joueur2.cartesDuJoueur.size() > 0) {

                // permet de gérer le type d'avancement de la partie dans le menu main
                switch (typeDAvancement){
                    case 1:
                        // toutes les 2s de nouvelles cartes sont dévoilées
                        avancementAutomatique();
                        break;
                    case 2:
                        //le joueur doit rentrer une touche dans la console afin de dévoiler de nouvelles cartes
                        avancementTourParTour();
                        break;
                }


                System.out.println("\n\n----- Tour " + j + " manche " + i + " jouée -----\n");
                //Les cartes passent des "cartesJoueur" à "Terrain joueur"
                jouerCarte(joueur1, joueur2);
                //affichage des dernières cartes de la liste Terrain joueur pour rendre visible au joueur la carte
                afficherLesCartes();
                // compare les valeurs des cartes pour permettre la suite du traitement
                valeur = comparerDernieresCartes();

                //si la valeur est égale à 0
                //on vérifie que les joueurs ont un nombre de cartes suffisant pour faire la méthode sandwich
                //on réalise la méthode sandwich
                //on affiche la nouvelle dernière carte de la liste terrainJoueur
                while (valeur == 0) {
                    verificationNombreMinCartes();
                    sandwich();
                    valeur = comparerDernieresCartes();
                    System.out.println("\négalité des cartes");
                    System.out.println("Sandwich");
                    afficherLesCartes();
                }

                // si la valeur > 0 le joueur 1 gagne, on transfère toutes les cartes dans sa défausse
                // si la valeur < 0 le joueur 2 gagne, on transfère toutes les cartes dans sa défausse
                if (valeur > 0) {
                    transfererLesCartes(joueur1,joueur2.terrainJoueur,joueur1.defausseJoueur);
                    transfererLesCartes(joueur1,joueur1.terrainJoueur,joueur1.defausseJoueur);
                    System.out.println("\n"+joueur1.getNom()+" gagne");

                } else {
                    transfererLesCartes(joueur2,joueur1.terrainJoueur,joueur2.defausseJoueur);
                    transfererLesCartes(joueur2,joueur2.terrainJoueur,joueur2.defausseJoueur);
                    System.out.println("\n"+joueur2.getNom()+" gagne");
                }
                i++;

            }
            //permet de vérifier s'il reste des cartes aux joueurs
            verificationCartesJoueurs();
            //permet de remettre toutes les cartes dans la liste cartesJoueur puis de les mélanger
            reconstituerLesDecks();
            j++;

        }while (continuerPartie);
    }

    // permet de comparer la valeur des dernières cartes de la liste cartesTerrain
    public int comparerDernieresCartes() {
        int valeur = joueur1.valeurCarteTerrain() - joueur2.valeurCarteTerrain();
        return valeur;
    }


    //permet d'afficher la dernière carte d'une liste de carte d'un joueur
    public void afficher(Joueur j, ArrayList<UneCarte> lc) {
        j.afficherDerniereCarte(lc);
    }

    // permet d'afficher les dernières cartes de la liste cartesTerrain
    public void afficherLesCartes(){
        afficher(joueur1,joueur1.terrainJoueur);
        afficher(joueur2,joueur2.terrainJoueur);
    }

    // la méthode sandwich permet de tirer deux cartes de la liste cartesJoueur vers terrainJoueur
    // par la suite, la dernière carte sera comparée (cf comparerDernieresCartes())
    public void sandwich() {
        joueur1.sandwich();
        joueur2.sandwich();
    }


    // permet de rassembler toutes les cartes du joueur dans la liste cartesDuJoueur
    // ensuite les cartes sont mélangées
    public void reconstituerDeck(Joueur joueur) {
        joueur.cartesDuJoueur.addAll(joueur.defausseJoueur);
        joueur.defausseJoueur.clear();
        melanger(joueur);
    }

    // permet de rassembler les cartes pour les deux joueurs dans leur liste cartesDuJoueur
    // les cartes sont ensuite mélangées
    public void reconstituerLesDecks(){
        reconstituerDeck(joueur1);
        reconstituerDeck(joueur2);
    }

    // permet de changer aléatoirement l'ordre des cartes de la liste cartesDuJoueur
    public void melanger(Joueur joueur) {
        Random random = new Random();
        for (int i = 0; i < joueur.cartesDuJoueur.size(); i++) {
            Collections.swap(joueur.cartesDuJoueur, i, random.nextInt(joueur.cartesDuJoueur.size()));
        }
    }

    // permet de transférer l'ensemble des cartes d'une liste à une autre
    public void transfererLesCartes(Joueur joueur, ArrayList<UneCarte> enleverCarte, ArrayList<UneCarte> ajouterCarte) {
        joueur.transfererLesCartes(enleverCarte, ajouterCarte);
    }

    // permet de transférer un nombre défini de cartes d'une liste à une autre
    public void transfertDeCartes(Joueur joueur, int nbreDeCartes, ArrayList<UneCarte> enleverCarte, ArrayList<UneCarte> ajouterCarte) {
        joueur.transfertDeCartes(nbreDeCartes, enleverCarte, ajouterCarte);

    }

    //méthode permettant de vérifier si la méthode sandwich peut être appliquée
    // pour cela il faut qu'il reste deux cartes aux joueurs dans la liste carteDuJoueur avant la méthode sandwich
    //pour chaque carte qu'il manque, le ou les joueur(s) vont aller chercher dans d'autres listes de cartes
    // dans l'ordre :
    // 1 - le joueur va chercher dans sa défausse
    // 2- le joueur va chercher dans la défausse du joueur adverse
    // 3- le joueur va chercher dans les cartesDuJoueur adverse
    public void verificationNombreMinCartes(){
        if (joueur1.cartesDuJoueur.size() < 3) {
            if (joueur1.defausseJoueur.size() > 0) {
                transfertDeCartes(joueur1, (3 - joueur1.cartesDuJoueur.size()), joueur1.defausseJoueur, joueur1.cartesDuJoueur);
            } else if (joueur2.defausseJoueur.size() > 0) {
                transfertDeCartes(joueur1, (3 - joueur1.cartesDuJoueur.size()), joueur2.defausseJoueur, joueur1.cartesDuJoueur);
            } else if (joueur2.cartesDuJoueur.size() > 3) {
                transfertDeCartes(joueur1, (3 - joueur1.cartesDuJoueur.size()), joueur2.cartesDuJoueur, joueur1.cartesDuJoueur);
            }
        }
        if (joueur2.cartesDuJoueur.size() < 3) {
            if (joueur2.defausseJoueur.size() > 0) {
                transfertDeCartes(joueur2, (3 - joueur2.cartesDuJoueur.size()), joueur2.defausseJoueur, joueur2.cartesDuJoueur);
            } else if (joueur1.defausseJoueur.size() > 0) {
                transfertDeCartes(joueur2, (3 - joueur2.cartesDuJoueur.size()), joueur1.defausseJoueur, joueur2.cartesDuJoueur);
            } else if (joueur1.cartesDuJoueur.size() > 3) {
                transfertDeCartes(joueur2, (3 - joueur2.cartesDuJoueur.size()), joueur1.cartesDuJoueur, joueur2.cartesDuJoueur);
            }
        }

    }

    // permet de vérifier s'il reste des cartes aux joueurs
    public void verificationCartesJoueurs(){
        if(joueur1.nombreTotalCartes()==0 || joueur2.nombreTotalCartes() == 0){
            continuerPartie = false;
        }
    }

    //permet de savoir quel joueur possède encore des cartes et de le déclarer vainqueur
    // les cartes sont ensuite supprimées si l'on souhaite faire une nouvelle partie
    public void declarerVainqueur(){
        if (joueur1.cartesDuJoueur.size() > 0) {
            System.out.println(joueur1.getNom()+" a gagné la partie ! ");
            joueur1.supprimerCartes();

        } else {
            System.out.println(joueur2.getNom()+" a gagné la partie ! ");
            joueur2.supprimerCartes();
        }

        continuerPartie = true;

    }

    // permet au joueur d'appuyer sur une touche pour continuer à faire avancer la partie et dévoiler une nouvelle manche
    public void avancementTourParTour(){

            do {
                Scanner lectureClavier = new Scanner(System.in);
                System.out.print("\n\n\nPour dévoiler les prochaines cartes appuyer sur '1' : ");
                continuer = lectureClavier.nextByte();
            } while (continuer != 1);

    }

    // permet une pause de 2 secondes entre deux manches
    public void avancementAutomatique() {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException iE) {
                System.out.println("Problème sur l'avancement automatique");
            }

    }

    //permet de rassembler les différentes étapes du jeu
    public void deroulementJeu() throws IOException {
        distribuer();
        manche();
        declarerVainqueur();
    }


}
