package TicTacToe.Models;

public class Player {
    private Symbol symbol;
    private int index;

    public Player(int index, Symbol symbol) {
        this.index = index;
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return this.symbol;
    }

    public int getIndex() {
        return this.index;
    }
}
