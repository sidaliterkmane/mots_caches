public class Cell {
    private int row, col;
    private char value;

    public Cell(int row, int col, char value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {return row;}
    public int getCol() {return col;}
    public char getValue() {return value;}
}
