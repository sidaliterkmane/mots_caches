import java.util.ArrayList;
import java.util.List;

public class Grid {
    private int M; // Nombre de lignes
    private int N; // Nombre de colonnes
    private char[][] grid;

    public Grid(int M, int N, char[][] grid) {
        this.M = M;
        this.N = N;
        this.grid = grid;
    }

    public int getM() {
        return M;
    }

    public int getN() {
        return N;
    }
}
