import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Sinon on peut utiliser un scanner? Pour utiliser nextInt par exemple

public class FileManager{

    private static void file(String[] args){

        String fileName = args[0];

        //créer un tableau dans lequel on ajoute soit une case par
        //line ou autre type de tableau?

        try{

            Scanner scan = new Scanner(new File(fileName));
            int i = 1; //numero de grille
            ArrayList<Grid> gridTable = new ArrayList<Grid>();

            while (scan.hasNext()){

                String gridName= "Grid" + i;
                int nbCol = scan.nextInt();
                int nbLines = scan.nextInt();
                int j=1; //pour savoir quand on arrive à la fin d'une grille


            }


        }   catch (FileNotFoundException ex){
            System.out.println("Erreur à l'ouverture du fichier");
        }

    }

}