// La classe "Cell" définit une cellule dans une grille avec 3 attributs, soit 
// row, col et value.
public class Cell {
    private int row, col; // Coordonnées de la cellule dans une grille.
    private char value;   // Caractère associé à une cellule.

    // Constructeur.
    public Cell(int row, int col, char value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    // Getters.
    public int getRow() {return row;}
    public int getCol() {return col;}
    public char getValue() {return value;}
}
