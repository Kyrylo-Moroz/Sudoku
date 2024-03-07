import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SudokuBoardSaver {
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
    String CSV_FILE_PATH = "sudoku_board_saved" + timeStamp + ".csv";

    public void saveBoardToCSV(JTextField[][] board) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH)) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    String value = board[i][j].getText();
                    boolean isEditable = board[i][j].isEditable();
                    writer.append(value);
                    writer.append(",");
                    writer.append(String.valueOf(isEditable));
                    if (j != board[i].length - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
            System.out.println("Plansza została zapisana: " + CSV_FILE_PATH);
        } catch (IOException e) {
            System.out.println("Błąd zapisu: " + e.getMessage());
        }
    }
}
