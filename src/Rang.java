public enum Rang {

    // Enumération des différents rangs que peut prendre une carte
    // association d'une valeur pour comparer les rangs des cartes
    DEUX(1),
    TROIS(2),
    QUATRE(3),
    CINQ(4),
    SIX(5),
    SEPT(6),
    HUIT(7),
    NEUF(8),
    DIX(9),
    VALET(10),
    DAME(11),
    ROI(12),
    AS(13);

    int valeur;

    // constructeur de l'énumération Rang
    Rang(int value){
        this.valeur=value;
    }

    // Constructeur afin de récupérer la valeur de la variable valeur
    public int getValeur() {
        return valeur;
    }

}
