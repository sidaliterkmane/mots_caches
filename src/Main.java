import java.io.File;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        // S'assurer que le nom du ficher est correctement donne
        if (args.length < 1) {
            System.out.println("Please provide the path to the input file.");
            return;
        }

        String filePath = args[0];

        // Cree une instance de fileManager et itere a travers le fichier input
        FileManager fileManager = new FileManager(filePath);
        fileManager.parseInputFile();

        // Collecte les grilles et les listes de mots
        List<Grid> grids = fileManager.getGrids();
        List<List<String>> wordLists = fileManager.getWordLists();

        // Pour chaque grille et liste de mots, execute la recherche de mots et imprime le resultat
        for (int i = 0; i < grids.size(); i++) {
            Grid grid = grids.get(i);
            List<String> wordList = wordLists.get(i);

            WordSearch wordSearch = new WordSearch(grid, wordList);

            // Execute la recherche de mots
            TreeSet<String> results = wordSearch.findWords();

            // Imprime le resultat dans le format specifie dans le TP
            System.out.println("Query " + (i + 1) + ":");
            for (String result : results) {
                System.out.println(result);
            }
            System.out.println();  // Separe les resultats des differents queries avec une ligne vide
        }
    }

    // Helper method pour imprimer une grille
    private static void printGrid(Grid grid) {
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                System.out.print(grid.getCell(i, j).getValue() + " ");
            }
            System.out.println();  // Saute une ligne apres avoir imprime une rangee
        }
        // Ex: Imprime neighbors pour la cellule (1,1)
        printNeighbors(grid, grid.getCell(1, 1));
    }


    // Helper method qui imprime les voisins d'une cellule donnee
    private static void printNeighbors(Grid grid, Cell cell) {
        List<Cell> neighbors = grid.getNeighbors(cell);
        System.out.println("Neighbors of cell (" + cell.getRow() + ", " + cell.getCol() + ") with value '" + cell.getValue() + "':");
        for (Cell neighbor : neighbors) {
            System.out.println("(" + neighbor.getRow() + ", " + neighbor.getCol() + ") -> '" + neighbor.getValue() + "'");
        }
    }
}