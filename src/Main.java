import java.io.File;
import java.util.List;

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

        // Imprime les grilles et les listes de mots
        for (int i = 0; i < grids.size(); i++) {
            printGrid(grids.get(i));
            System.out.println("Words to search : " + wordLists.get(i));
            System.out.println("---------");
        }
    }

    // Helper method pour imprimer une grid
    private static void printGrid(Grid grid) {
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                System.out.print(grid.getCell(i, j).getValue() + " ");
            }
            System.out.println();  // Move to the next line after printing a row
        }
    }

}