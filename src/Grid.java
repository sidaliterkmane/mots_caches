// Packages que nous utilisons dans "Grid".
import java.util.ArrayList;
import java.util.List;

// La classe "Grid" sera utilisée pour instancier une grille à partir du fichier
// d'entrées. 
public class Grid {
    private int rows;       // Nombre de lignes
    private int cols;       // Nombre de colonnes
    private Cell[][] grid;  // Tableau 2D de cellules constituant la grille.

    // Constructeur.
    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
    }

    // Getters.

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // Méthode initialisant une cellule à une position et valeur données 
    // selon la valeur de row et col.
    public void setCell(int row, int col, char value) {
        grid[row][col] = new Cell(row, col, value);
    }

    // Méthode retournant l'objet cellule d'une position donnée.
    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    // Méthode trouvant toutes les cellules voisines d'une cellule donnée.
    public List<Cell> getNeighbors(Cell cell) {

        // Une liste qui emmagasine les cellules voisines.
        List<Cell> neighbors = new ArrayList<>();

        // Définit les directions horizontales, verticales et diagonales des voisins.
        // Chaque paire représente un mouvement dans la rangée et la colonne.
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},            {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] direction : directions) {
            int newRow = cell.getRow() + direction[0];
            int newCol = cell.getCol() + direction[1];

            // Vérifie les bordures pour s'assurer que la nouvelle rangée et colonne sont
            // bel et bien dans la grille.
            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                Cell neighbor = grid[newRow][newCol];
                if (neighbor != null) {
                    neighbors.add(neighbor);
                }
            }
        }

        return neighbors;
    }
}