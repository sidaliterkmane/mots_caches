// Packages utilisés dans la classe "FileManager".
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// La classe "FileManager" va s'occuper de faire la gestion du fichier d'entrées.
public class FileManager{
    private String fileName;                // Nom du fichier.
    private List<Grid> grids;               // Liste de grilles.
    private List<List<String>> wordLists;   // Liste de listes de mots recherchés.

    //Constructeur.
    public FileManager(String fileName) {
        this.fileName = fileName;
        this.grids = new ArrayList<>();
        this.wordLists = new ArrayList<>();
    }

    // Méthode qui fait la lecture du fichier d'entrées pour créer les grilles et les 
    // listes de mots utilisées lors de la résolution de grilles de mots cachés.
    public void parseInputFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;

            // Lire le fichier jusqu'à ce qu'il n'y ait plus de lignes.
            while ((line = reader.readLine()) != null) {

                // Séparer les dimensions en 2 entiers déterminer le nombre de rangées
                // et de colonnes d'une grille.
                String[] dimensions = line.split(" ");
                int rows = Integer.parseInt(dimensions[0]);
                int cols = Integer.parseInt(dimensions[1]);

                // Créer une nouvelle grille avec les dimensions données
                Grid grid = new Grid(rows, cols);

                // Lire chaque caractères d'une grille en lisant chaque colonne d'une rangée
                // jusqu'à la dernière rangée.
                for (int i = 0; i < rows; i++) {
                    line = reader.readLine();
                    for (int j = 0; j < cols; j++) {
                        grid.setCell(i, j, line.charAt(j * 2)); // j * 2 pour ne pas tenir compte des espaces
                    }
                }

                // Ajout d'une grille à la liste de grilles.
                grids.add(grid);
                
                // Création d'une chaîne de caractère représentant la prochaine ligne
                // qui contient la collection de mots.
                line = reader.readLine();

                // Création de la liste de mots qui étaient préalablement séparés par des
                // espaces dans "line".
                List<String> words = new ArrayList<>(Arrays.asList(line.split(" ")));

                // Ajout des listes de mots "words" à la liste de liste de mots "wordLists".
                wordLists.add(words);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters.

    public List<Grid> getGrids() {
        return grids;
    }

    public List<List<String>> getWordLists() {
        return wordLists;
    }

}