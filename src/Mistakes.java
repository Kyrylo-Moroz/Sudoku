import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Mistakes {
    private JTextField[][] board;
    private Map<int[], String> removedValues;

    public Mistakes(JTextField[][] board, Map<int[], String> removedValues) {
        this.board = board;
        this.removedValues = removedValues;
    }

    public void checkMistakes() {
        for (Map.Entry<int[], String> entry : removedValues.entrySet()) {
            int[] coordinates = entry.getKey();
            String removedValue = entry.getValue();

            JTextField field = board[coordinates[0]][coordinates[1]];
            String enteredValue = field.getText();

            if (enteredValue.isEmpty()) {
                field.setBackground(Color.WHITE);
            } else if (enteredValue.equals(removedValue)) {
                field.setBackground(Color.WHITE);
            } else {
                field.setBackground(Color.RED);
            }
        }
    }
}