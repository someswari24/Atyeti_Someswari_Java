package ControlStatements.ticTacToeGame;

public class TicTacToe {
    private char[][] board;
    private char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    public void initializeBoard() {
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                board[i][j]=' ';
    }

    public void printBoard(){
        System.out.println("-------------");
        for(int i=0;i<3;i++){
            System.out.print("| ");
            for(int j=0;j<3;j++){
                System.out.print(board[i][j]+" | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean checkWin(){
        for(int i=0;i<3;i++){
            if((board[i][0]==currentPlayer) &&
                    (board[i][1]==currentPlayer)&&
                    (board[i][2]==currentPlayer)) return true;

            if((board[0][i]==currentPlayer) &&
                    (board[1][i]==currentPlayer)&&
                    (board[2][i]==currentPlayer)) return true;
        }

        if((board[0][0]==currentPlayer)&&
             (board[1][1]==currentPlayer) && (board[2][2]==currentPlayer)) return true;

        if((board[0][2]==currentPlayer)&&
                (board[1][1]==currentPlayer) && (board[2][0]==currentPlayer)) return true;
    return false;
    }

    public void switchPlayer(){
        if(currentPlayer=='X') currentPlayer='O';
        else currentPlayer='X';
    }

    public boolean isValidMove(int row,int column){
        return row>=0 && row<3 && column>=0 && column<3 && board[row][column]==' ';
    }

    public boolean isBoardFull(){
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                if(board[i][j]==' ') return false;

        return true;
    }

    public void makeMove(int row,int column){
        board[row][column]=currentPlayer;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

}
