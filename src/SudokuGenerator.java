import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class SudokuGenerator {
    private static final int GRID_SIZE = 9;
    private static Map<int[], String> removedValues;
    public static void generateBoard(SudokuBoard sudokuBoard, String difficulty) {
        int minFilledCells = 0;
        if (difficulty.equals("łatwy")) {
            minFilledCells = 41;
        } else if (difficulty.equals("średni")) {
            minFilledCells = 48;
        } else if (difficulty.equals("trudny")) {
            minFilledCells = 55;
        }

        SudokuBoardSolver solver = new SudokuBoardSolver(sudokuBoard.board);
        solver.solver();

        Random random = new Random();

        removedValues = new LinkedHashMap<>();

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (random.nextInt(81) < minFilledCells) {
                    String value = sudokuBoard.board[i][j].getText();
                    sudokuBoard.board[i][j].setText("");
                    sudokuBoard.board[i][j].setEditable(true);

                    int[] coordinates = {i, j};
                    removedValues.put(coordinates, value);
                } else {
                    sudokuBoard.board[i][j].setEditable(false);
                }
            }
        }
    }
    public static Map<int[], String> getRemovedValues() {
        return removedValues;
    }
}