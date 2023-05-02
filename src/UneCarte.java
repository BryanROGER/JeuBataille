public class UneCarte {


    private final Rang rang;
    private final Enseigne enseigne;
    boolean visible = false;

    // Constructeur de la classe UneCarte nécessitant un Rang et une Enseigne
    public UneCarte(Enseigne enseigne,Rang rang){
        this.enseigne=enseigne;
        this.rang=rang;
    }

    //Permet de récupérer le rang de la carte
    public Rang getRang() {
        return rang;
    }

    //Permet de récupérer l'enseigne de la carte
    public Enseigne getEnseigne() {
        return enseigne;
    }

    // Permet de connaitre si la carte est visible ou non
    public boolean isFlip() {
        return visible;
    }

    // Méthode permettant de changer la visibilité d'une carte passant d'un état à son opposé
    public boolean flipCarte(){
        return visible = !visible;
    }

    //Methode permettant d'afficher une carte (rang + enseigne)
    public void afficherCarte(){
        System.out.println( getRang() + " de "+ getEnseigne());
    }

    // Methode permettant de récupérer la valeur de la carte en faisant appelle à la méthode de l'énumération Rang
    public int getValeur(){
        return rang.getValeur();
    }
}
