import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager{
    private String fileName;
    private List<Grid> grids;
    private List<List<String>> wordLists;

    public FileManager(String fileName) {
        this.fileName = fileName;
        this.grids = new ArrayList<>();
        this.wordLists = new ArrayList<>();
    }

    public void parseInputFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            // Lire le fichier jusqu'a ce qu'il n'y ait plus de lignes
            while ((line = reader.readLine()) != null) {
                // Separer les dimensions en 2 int pour rangees et cols
                String[] dimensions = line.split(" ");
                int rows = Integer.parseInt(dimensions[0]);
                int cols = Integer.parseInt(dimensions[1]);

                // Creer une nouvelle grille avec les dimensions donnees
                Grid grid = new Grid(rows, cols);

                // Lire les prochaines rangees*colonnes pour remplir la grille
                for (int i = 0; i < rows; i++) {
                    line = reader.readLine();
                    for (int j = 0; j < cols; j++) {
                        grid.setCell(i, j, line.charAt(j * 2)); // j * 2 pour skip les espaces
                    }
                }

                grids.add(grid);

                // Lis la prochaine ligne qui contient la collection de mots
                line = reader.readLine();

                List<String> words = new ArrayList<>(Arrays.asList(line.split(" ")));

                // Ajoute les listes de mots a la liste de liste de mots
                wordLists.add(words);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Grid> getGrids() {
        return grids;
    }

    public List<List<String>> getWordLists() {
        return wordLists;
    }

}