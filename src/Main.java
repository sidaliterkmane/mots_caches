// Sid Ali Terkmane et Léane Lavigne - 2023/10/26

// Ce programme à pour but de résoudre un jeu de mots cachés. Prends en entrée un fichier contenant des grilles de
// mots cachés ainsi des listes de mots à trouver dans chaque grille. Imprime chaque possibilité de chemin
// à prendre pour chacun des mots en ordre lexicographique.

// Packages utilisés dans "Main".
import java.io.File;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {

        // Pour s'assurer que le nom du ficher est correctement donné.
        if (args.length < 1) {
            System.out.println("Please provide the path to the input file.");
            return;
        }

        String filePath = args[0];

        // Création d'une instance de fileManager et itération à travers le fichier d'entré.
        FileManager fileManager = new FileManager(filePath);
        fileManager.parseInputFile();

        // Pour emmagasiner les grilles et les listes de mots.
        List<Grid> grids = fileManager.getGrids();
        List<List<String>> wordLists = fileManager.getWordLists();

        // Pour chaque grille et liste de mots, la recherche de mots est exécutée et le resultat
        // est imprimé.
        for (int i = 0; i < grids.size(); i++) {
            Grid grid = grids.get(i);
            List<String> wordList = wordLists.get(i);

            WordSearch wordSearch = new WordSearch(grid, wordList);

            // Exécution de la recherche de mots.
            TreeSet<String> results = wordSearch.findWords();

            // Pour imprimer le resultat dans le format specifié dans l'énoncé du TP.
            System.out.println("Query " + (i + 1) + ":");
            for (String result : results) {
                System.out.println(result);
            }
            System.out.println();  // Sépare les résultats des differents queries avec une ligne vide.
        }
    }
}