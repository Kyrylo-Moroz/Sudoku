import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SudokuBoardReader {
    public void loadBoardFromCSV(JTextField[][] boardLoader) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile.getName().endsWith(".csv")) {
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    String line;
                    int i = 0;
                    while ((line = reader.readLine()) != null && i < boardLoader.length) {
                        String[] values = line.split(",");
                        for (int j = 0; j < values.length && j < boardLoader[i].length * 2; j += 2) {
                            String value = values[j];
                            String editableValue = values[j + 1];
                            int column = j / 2;
                            boardLoader[i][column].setText(value);
                            boardLoader[i][column].setEditable(Boolean.parseBoolean(editableValue));
                        }
                        i++;
                    }
                    System.out.println("Plansza została wczytana z pliku: " + selectedFile.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println("Błąd odczytu: " + e.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wybrany plik nie jest typu .csv. Wybierz inny plik.", "ERROR", JOptionPane.ERROR_MESSAGE);
                loadBoardFromCSV(boardLoader);
            }
        }
    }
}