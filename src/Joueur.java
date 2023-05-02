import java.util.ArrayList;

public class Joueur {

    private String nom;
    ArrayList<UneCarte> cartesDuJoueur = new ArrayList<>();
    ArrayList<UneCarte> defausseJoueur = new ArrayList<>();
    ArrayList<UneCarte> terrainJoueur = new ArrayList<>();

    // Constructeur de la classe Joueur
    public Joueur(String nomJoueur){
        nom = nomJoueur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nomJoueur){
        this.nom = nomJoueur;
    }

    // Méthode permettant d'ajouter une carte de CarteDuJoueur à TerrainJoueur représentant l'action de jouer une carte
    public void jouerCarte(){
        UneCarte temp = cartesDuJoueur.get(0);
        cartesDuJoueur.remove(0);
        terrainJoueur.add(temp);
    }

    // Méthode permettant de récupérer la valeur de la dernière carte arrivée dans la liste TerrainJoueur
    public int valeurCarteTerrain (){
        UneCarte c = terrainJoueur.get(terrainJoueur.size()-1);
        return c.getValeur();
    }

    //Méthode permettant d'afficher la dernière d'une liste
    public void afficherDerniereCarte(ArrayList<UneCarte> lc){
        UneCarte c = lc.get(lc.size()-1);
        System.out.print(getNom() + " : ");
        c.afficherCarte();
    }


    // Méthode permettant d'appliquer la règle du sandwich, règle propre à la bataille
    // si égalité des valeurs des cartes, on joue deux cartes
    public void sandwich(){
        jouerCarte();
        jouerCarte();
    }

    //Méthode permettant de transférer toutes les cartes d'une liste à une autre
    public void transfererLesCartes(ArrayList<UneCarte> enleverCarte,ArrayList<UneCarte> ajouterCarte){
        ajouterCarte.addAll(enleverCarte);
        enleverCarte.clear();
    }

    // Methode permettant de transferer un nombre défini de cartes d'une liste à une autre
    public void transfertDeCartes(int nbreCartesATransferer,ArrayList<UneCarte> enleverCarte,ArrayList<UneCarte> ajouterCarte){
        for(int i = 0;i<nbreCartesATransferer;i++){
            UneCarte c = enleverCarte.get(0);
            ajouterCarte.add(c);
            enleverCarte.remove(0);
            i++;
        }
    }

    // Méthode permettant de calculer le nombre total de cartes du joueur
    public int nombreTotalCartes(){
        return cartesDuJoueur.size() + defausseJoueur.size() + terrainJoueur.size();
    }

    // permet de supprimer l'ensemble des cartes de la liste carteDuJoueur
    public void supprimerCartes(){
        cartesDuJoueur.clear();
    }

}
