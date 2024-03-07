import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SudokuBoardSolver {

    private JTextField[][] board;
    private static final int GRID_SIZE = 9;

    public SudokuBoardSolver(JTextField[][] board) {
        this.board = board;
    }

    public void solver() {
        generateSudoku();

    }

    private void generateSudoku() {
        solveSudoku();
        //printSudoku();
    }
    private void printSudoku() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(board[i][j].getText() + " ");
            }
            System.out.println();
        }
    }


    private boolean solveSudoku() {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;


        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (board[i][j].getText().isEmpty()) {
                    row = i;
                    col = j;


                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }


        if (isEmpty) {
            return true;
        }


        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= GRID_SIZE; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        for (int num : numbers) {
            if (isSafe(row, col, num)) {

                board[row][col].setText(String.valueOf(num));


                if (solveSudoku()) {
                    return true;
                }


                board[row][col].setText("");
            }
        }


        return false;
    }

    private boolean isSafe(int row, int col, int num) {

        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col].getText().equals(String.valueOf(num))) {
                return false;
            }
        }


        for (int j = 0; j < GRID_SIZE; j++) {
            if (board[row][j].getText().equals(String.valueOf(num))) {
                return false;
            }
        }


        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol].getText().equals(String.valueOf(num))) {
                    return false;
                }
            }
        }

        return true;
    }
}
