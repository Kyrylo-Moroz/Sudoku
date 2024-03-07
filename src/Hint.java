import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Hint {
    private JTextField[][] board;
    private Map<int[], String> removedValues;

    public Hint(JTextField[][] board, Map<int[], String> removedValues) {
        this.board = board;
        this.removedValues = removedValues;
    }

    public void addHint() {
        List<int[]> emptyCells = findEmptyCells();
        if (emptyCells.isEmpty()) {
            return;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(emptyCells.size());
        int[] coordinates = emptyCells.get(randomIndex);

        for (Map.Entry<int[], String> entry : removedValues.entrySet()) {
            int[] key = entry.getKey();
            String value = entry.getValue();

            if (key[0] == coordinates[0] && key[1] == coordinates[1]) {
                board[coordinates[0]][coordinates[1]].setText(value);
                return;
            }
        }
    }

    private List<int[]> findEmptyCells() {
        List<int[]> emptyCells = new ArrayList<>();

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col].getText().isEmpty()) {
                    emptyCells.add(new int[]{row, col});
                }
            }
        }

        return emptyCells;
    }
}