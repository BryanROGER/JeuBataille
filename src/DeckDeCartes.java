import java.util.*;

public class DeckDeCartes {

    //Constitution d'une liste de carte pour créer un deck de carte
        private final ArrayList<UneCarte> deckDeCartes;


        // Constructeur de la classe DeckDeCartes
        // permet de créer une carte pour chaque Rang de chaque enseigne
        // la méthode mélanger() permet de mettre les cartes dans le désordre
        public DeckDeCartes(){
        deckDeCartes = new ArrayList<>();
        for(Enseigne e : Enseigne.values()){
            for(Rang r : Rang.values()){
                deckDeCartes.add(new UneCarte(e,r));
            }
        }
        melanger();
        }


        //Permet d'accéder à la liste deckDeCarte
        public ArrayList<UneCarte> getDeckDeCartes() {
        return deckDeCartes;
        }

        // Méthode permettant d'afficher chacune des cartes du deck de cartes
        // fait appel à la méthode de la classe UneCarte
        public void afficherCartes(){
        for(int i = 0; i<deckDeCartes.size();i++){
            UneCarte temp = deckDeCartes.get(i);
            temp.afficherCarte();
        }
        }


        // Méthode permettant de changer l'ordre des cartes dans la liste deckDeCarte
        public void melanger(){
            Random random = new Random();
            for(int i = 0; i <deckDeCartes.size();i++){
                Collections.swap(getDeckDeCartes(),i,random.nextInt(deckDeCartes.size()));
            }
        }


        // méthode permettant de connaitre la taille de la liste deckDeCartes
        public int sizeDeck(){
        return deckDeCartes.size();
        }



        // méthode permettant un accès à une carte d'indice 'i' issue de la liste deckDeCarte
        public UneCarte getCarte(int i){
        return deckDeCartes.get(i);
        }

}

