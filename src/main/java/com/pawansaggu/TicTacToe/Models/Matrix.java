package TicTacToe.Models;

public class Matrix {
    private Symbol matrix[][];
    private int row;
    private int col;
    private int filledPlaces;

    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        this.matrix = new Symbol[row+1][col+1];
        this.filledPlaces = 0;
    }

    public boolean isFilled() {
        if (this.filledPlaces == this.row * this.col) {
            return true;
        }

        return false;
    }

    public Symbol[][] geMatrix() {
        return this.matrix;
    }

    public void fillPlace() {
        this.filledPlaces += 1;
    }

    public void setInput(int x, int y, Symbol input) {
        this.matrix[x][y] = input;

        this.fillPlace();
    }

    public Symbol getValue (int x, int y) {
        return this.matrix[x][y];
    }
}
