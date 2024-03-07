package Print;

import javax.swing.*;
import java.io.*;

public class TablePrinter {
    public void print() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wybierz plik CSV");
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String csvFile = fileChooser.getSelectedFile().getAbsolutePath();
            String line;
            String delimiter = ",";

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter("Sudoku.txt"))) {

                while ((line = reader.readLine()) != null) {
                    String[] row = line.split(delimiter);

                    for (int i = 0; i < row.length; i++) {
                        writer.write(row[i]);
                        if (i < row.length - 1) {
                            writer.write(" | ");
                        }
                    }
                    writer.newLine();

                    if (reader.ready()) {
                        writer.write("-----------------------------------");
                        writer.newLine();
                    }
                }

                JOptionPane.showMessageDialog(null, "Plik 'Sudoku.txt' z tabelą został pomyślnie utworzony.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
