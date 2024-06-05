package TicTacToe;

import java.util.HashMap;
import java.util.Map;

import TicTacToe.Exceptions.AlreadyFilledException;
import TicTacToe.Exceptions.InvalidInputException;
import TicTacToe.Models.Matrix;
import TicTacToe.Models.Player;
import TicTacToe.Models.Symbol;

public class TicTacToe {
    private Matrix matrix;
    private int currentTurn;
    private int totalPlayers;
    private int row;
    private int col;
    private Map<Integer, Symbol> playerSymbolMap;
    private Player players[];


    public TicTacToe(int row, int col, Player players[]) {
        this.row = row;
        this.col = col;
        this.matrix = new Matrix(row, col);
        this.currentTurn = 0;
        this.totalPlayers = players.length;
        this.players = players;
        this.playerSymbolMap = new HashMap<Integer, Symbol>();

        initializePlayers();
    }

    private void initializePlayers() {
        for (int i=0; i<this.players.length; i++) {
            this.playerSymbolMap.put(players[i].getIndex(), players[i].getSymbol());
        }
    }

    public void playTurn(int row, int col) throws Exception {
        Symbol input = this.playerSymbolMap.get(currentTurn);

        if (this.matrix.isFilled()) {
            throw new AlreadyFilledException("matrix already filled");
        }

        if (!validateInput(row, col, input)) {
            throw new InvalidInputException("invalid input");
        }

        this.matrix.setInput(row, col, input);

        this.incrementTurn();
        
        this.printMatrix();
    }

   

    private void incrementTurn() {
        this.currentTurn = (this.currentTurn + 1)%this.totalPlayers;
    }

    private boolean validateInput(int x, int y, Symbol symbol) {
        if (!(x > 0 && x <= this.row && y > 0 && y <= this.col)) {
            return false;
        }

        if (this.matrix.getValue(x, y) != null) {
            return false;
        }

        return true;
    }

    public boolean hasPlayerWon() {
        boolean rowMatch = false;
        boolean colMatch = false;
        boolean diagMatch = false;
        boolean antiDiagMatch = false;
        Symbol valueToCompare = null;

        for (int i = 1; i <= this.row; i++) {
            boolean hasWon = true;
            valueToCompare = this.matrix.getValue(i, 1);

            if (valueToCompare == null) {
                continue;
            }

            for (int j=2; j<=this.col; j++) {
                if (this.matrix.getValue(i, j) != valueToCompare) {
                    hasWon = false;
                    break;
                }
            }

            if (hasWon) {
                rowMatch = true;

                break;
            }
        }

        for (int i = 1; i <= this.col; i++) {
            boolean hasWon = true;
            valueToCompare = this.matrix.getValue(1, i);

            if (valueToCompare == null) {
                continue;
            }

            for (int j=2; j<=this.row; j++) {
                if (this.matrix.getValue(j, i) != valueToCompare) {
                    hasWon = false;
                    break;
                }
            }

            if (hasWon) {
                colMatch = true;

                break;
            }
        }

        
        
        valueToCompare = matrix.getValue(1, 1);
    
        for (int i = 2; i<=this.row; i++) {
            if (this.matrix.getValue(i, i) != valueToCompare) {
                diagMatch = false;

                break;
            }
        }

        valueToCompare = this.matrix.getValue(1, this.col);

        if (valueToCompare != null) {
            for (int i = 2; i  <=this.row; i++) {
                if (this.matrix.getValue(i, this.col - i + 1) != valueToCompare) {
                    antiDiagMatch = false;
    
                    break;
                }
            }
        }

       return colMatch || diagMatch || antiDiagMatch || rowMatch;
    }

    public int getCurrentTurn() {
        return this.currentTurn;
    }

    private void printMatrix() {
        for (int i=1; i<=this.row; i++) {
            for (int j=1; j<=this.col; j++) {
                System.out.print(matrix.getValue(i, j) + "| ");
            }

            System.out.println();
        }
    }

    public boolean isFilled() {
        return this.matrix.isFilled();
    }
}
