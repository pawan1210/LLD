package TicTacToe;

import java.util.Scanner;

import TicTacToe.Models.Player;
import TicTacToe.Models.Symbol;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Player players[] = new Player[2];
        players[0] = new Player(0, Symbol.O);
        players[1] = new Player(1, Symbol.X);
        TicTacToe ticTacToe = new TicTacToe(3, 3, players);
       
        while (true) {
            int currentTurn = ticTacToe.getCurrentTurn();

            System.out.println("Player " + currentTurn + " is playing");

            int row = s.nextInt();
            int col = s.nextInt();

            try {
                ticTacToe.playTurn(row, col);

                if (ticTacToe.hasPlayerWon()) {
                    System.out.println("Player" + currentTurn + "won");

                    break;
                }

                if (ticTacToe.isFilled()) {
                    System.out.println("Matrix filled, please play a new game");
                }
            } catch (Exception e) {
                System.out.println("Turn fails due to " + e.getMessage()+ "! Please retry");
            }
        }

        s.close();
    }
}
