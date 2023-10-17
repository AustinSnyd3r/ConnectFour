import java.util.Scanner;

public class ConnectFour{
    //board size
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    private char[][] board;
    private char currentPlayer;

    //constructor for game
    public ConnectFour(){
        board = new char[ROWS][COLUMNS];
        currentPlayer = 'X';
        initializeBoard();
    }

    //method to print the board during play
    public void printBoard(){
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                System.out.print(board[row][col] + "|");
            }
            System.out.println();
        }
    }

    //makes the starting board
    public void initializeBoard(){
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = ' ';
            }
        }
    }

    //checks for valid move by current player
    public boolean makeMove(int col){

        if (col < 0 || col >= COLUMNS || board[0][col] != ' ') {
            System.out.println("Invalid move. Try again.");
            return false;
        }

        for (int row = ROWS - 1; row >= 0; row--) {
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                return true;
            }
        }

        return false;
    }

    public boolean checkWin(){

        //check for horizontal wins
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] != ' '
                        && board[row][col] == board[row][col + 1]
                        && board[row][col] == board[row][col + 2]
                        && board[row][col] == board[row][col + 3]) {
                    return true;
                }
            }
        }

        //Check for vertical wins
        for (int row = 0; row <= ROWS - 4; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (board[row][col] != ' '
                        && board[row][col] == board[row + 1][col]
                        && board[row][col] == board[row + 2][col]
                        && board[row][col] == board[row + 3][col]) {
                    return true;
                }
            }
        }    

        //bottom left to top right checker
        for (int row = 0; row <= ROWS - 4; row++){
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] != ' '
                        && board[row][col] == board[row + 1][col + 1]
                        && board[row][col] == board[row + 2][col + 2]
                        && board[row][col] == board[row + 3][col + 3]) {
                    return true;
                }
            }
        }


        //bottom right to top left check. o(n^2)
        for (int row = 3; row < ROWS; row++){
            for (int col = 0; col <= COLUMNS - 4; col++) {
                if (board[row][col] != ' '
                        && board[row][col] == board[row - 1][col + 1]
                        && board[row][col] == board[row - 2][col + 2]
                        && board[row][col] == board[row - 3][col + 3]) {
                    return true;
                }
            }
        }    

        //if no wins detected
        return false;
    }

    //switch between players
    public void switchPlayer(){
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        Scanner scanner = new Scanner(System.in);

        //repeating game
        while (true) {
            game.printBoard();
            System.out.println("Player " + game.currentPlayer + ", enter your move (0-6):");
            int col = scanner.nextInt();

            if (game.makeMove(col)) {
                if (game.checkWin()) {
                    game.printBoard();
                    System.out.println("Player " + game.currentPlayer + " wins!");
                    break;
                }

                game.switchPlayer();
            }
        }
        scanner.close();
    }
    
}