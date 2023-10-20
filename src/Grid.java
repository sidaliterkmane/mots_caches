import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int rows; // Nombre de lignes
    private int cols; // Nombre de colonnes
    private Cell[][] grid;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    // Initialise une cellule a une position donnee selon la valeur de row et col
    public void setCell(int row, int col, char value) {
        grid[row][col] = new Cell(row, col, value);
    }

    // Retourne l'objet cellule d'une position donnee
    public Cell getCell(int row, int col) {
        return grid[row][col];
    }

    // Trouve toutes les cellules voisines d'une cellule donnee
    public List<Cell> getNeighbors(Cell cell) {
        // Une liste qui stoque les cellules voisines
        List<Cell> neighbors = new ArrayList<>();

        // Definit les directions horizontales, verticales et diagonales des voisins.
        // Chaque paire represente un mouvement dans la rangee et la colonne
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},            {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        for (int[] direction : directions) {
            int newRow = cell.getRow() + direction[0];
            int newCol = cell.getCol() + direction[1];

            // Verifie les bordures pour s'assurer que la nouvelle rangee et colonne sont
            // bel et bien dans la grille
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
