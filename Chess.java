import java.util.*;

public class Chess {

    public enum Piece {
        KNIGHT, QUEEN;
    }

    private Piece piece;

    Chess(Piece p) {
        this.piece = p;
    }

    /**
     * For boardSize X, see how many different ways X QUEENS can be placed on the board safely.
     */
    public ArrayList<String> getSolvesForBoardSize(int boardSize) {
        boolean[][] board = new boolean[boardSize][boardSize];
        return queens(board, 0); // start working on the first row.
    }

    private static ArrayList<String> queens(boolean[][] board, int row) {
        // If we've finished working on all the rows
        // without it failing then we have placed all the queens
        // and have a solution.
        if (row == board.length) {
            var retval = new ArrayList<String>();
            retval.add(toString(board, Piece.QUEEN));
            return retval;
        }

        ArrayList<String> solves = new ArrayList<String>();
        // placing the queen and checking for every row and col
        for (int col = 0; col < board.length; col++) {
            // place the queen if it is safe
            if (isSafe(board, row, col)) {
                board[row][col] = true;
                solves.addAll(queens(board, row+1));
                board[row][col] = false;
            }
        }
        return solves;
    }

    private static boolean isSafe(boolean[][] board, int row, int col) {
        // check vertical row
        for (int i=0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        // diagonal left
        int maxLeft = Math.min(row,col);
        for (int i = 1; i <= maxLeft; i++) {
            if (board[row-i][col-i]) {
                return false;
            }
        }

        // diagonal right
        int maxRight = Math.min(row,board.length - col - 1);
        for (int i = 1; i <= maxRight; i++) {
            if (board[row-i][col+i]) {
                return false;
            }
        }
        
        return true;
    }

    public static String toString(boolean[][] board, Piece piece) {
        StringBuilder sb = new StringBuilder();
        for (boolean[] row : board) {
            for (boolean element : row) {
                if (element) {
                    sb.append("[" + piece.toString().charAt(0) + "]");
                } else {
                    sb.append("[X]");
                }
            }
            //sb.append(System.lineSeparator());
            sb.append("\n");
        }
        return sb.toString();
    }
}
