import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws IOException {

        Scanner lectureClavier = new Scanner(System.in);
        DeroulementPartie deroulementPartie = new DeroulementPartie();
        byte choix;
        System.out.println("\nBienvenue au jeu de la bataille\n");

        //Mise en place d'un menu permettant à l'utilisateur d'interagir
        do{
            System.out.println("\nVeuillez saisir le chiffre correspondant à votre requête.");
            System.out.println("1- Nommer le joueur 1.");
            System.out.println("2- Nommer le joueur 2.");
            System.out.println("3- Lancer la partie en avancement automatique.");
            System.out.println("4- Lancer la partie en avancement tour par tour.");
            System.out.println("5- Lancer la partie en accéléré pour découvrir le vainqueur.");
            System.out.println("6- Quitter le jeu.");
            System.out.print("votre choix : ");
            choix = lectureClavier.nextByte();
            //Mise en place d'un switch pour orienter le choix de l'utilisateur
            switch (choix) {
                //Va permettre à l'utilisateur de changer le nom du premier joueur
                case 1:
                    String nomJ1;
                    System.out.print("Veuillez saisir le nom du joueur 1 : ");
                    nomJ1 = lectureClavier.next();
                    deroulementPartie.joueur1.setNom(nomJ1);
                    break;
                //Va permettre à l'utilisateur de changer le nom du second joueur
                case 2:
                    String nomJ2;
                    System.out.print("Veuillez saisir le nom du joueur 2 : ");
                    nomJ2 = lectureClavier.next();
                    deroulementPartie.joueur2.setNom(nomJ2);
                    break;
                // Modifie la variable typeDAvancement de la classe DeroulementPartie afin d'avoir une avance automatique
                case 3:
                    deroulementPartie.typeDAvancement = 1;
                    deroulementPartie.deroulementJeu();
                    break;
                // Modifie la variable typeDAvancement de la classe DeroulementPartie afin d'avoir une tour par tour
                case 4:
                    deroulementPartie.typeDAvancement = 2;
                    deroulementPartie.deroulementJeu();
                    break;
                // Permet d'avoir une partie qui se déroule jusqu'à déclarer un vainqueur
                case 5:
                    deroulementPartie.deroulementJeu();
                    break;
                // permet à l'utilisateur de quitter le menu
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.print("Veuillez choisir un chiffre du menu");
                    break;
            }
        // lorsque l'utilisateur rentre la valeur 6, cela met fin au programme
        }while (choix != 6);


    }


}