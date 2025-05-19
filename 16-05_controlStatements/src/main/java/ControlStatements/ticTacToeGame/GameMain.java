package ControlStatements.ticTacToeGame;

import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        TicTacToe ticTacToe=new TicTacToe();
        ticTacToe.printBoard();

        while (true){
            System.out.println("Player "+ticTacToe.getCurrentPlayer()+ "'s turn");

            int row;int column;
            while(true) {
                System.out.print("Enter row(0 1 2) :");
                row = scanner.nextInt();
                System.out.print("Enter column(0 1 2):");
                column = scanner.nextInt();
                System.out.println();

                if (ticTacToe.isValidMove(row, column)) {
                    ticTacToe.makeMove(row, column);
                    break;
                } else System.out.println("Invalid Move.Try Again");
            }

            ticTacToe.printBoard();

            if (ticTacToe.checkWin()){
                System.out.println("player "+ticTacToe.getCurrentPlayer()+" wins");
                break;
            }
            else if(ticTacToe.isBoardFull()) System.out.println("Board is full");

            ticTacToe.switchPlayer();

        }

    }

}
