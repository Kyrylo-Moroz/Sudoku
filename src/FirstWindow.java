import Print.TablePrinter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstWindow {
    public void showWindow1() {
        JFrame window1 = new JFrame();
        window1.setTitle("Sudoku");
        window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window1.setSize(500, 600);
        window1.getContentPane().setLayout(null);

        JButton buttonNowaGra = new JButton("Rozpocząć nową grę");
        buttonNowaGra.setSize(300, 75);
        buttonNowaGra.setLocation(100, 75);
        buttonNowaGra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Difficulty difficulty = new Difficulty();
                difficulty.showWindow();
                window1.dispose();
            }
        });

        JButton buttonImport = new JButton("Import");
        buttonImport.setSize(300, 75);
        buttonImport.setLocation(100, 175);
        buttonImport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SudokuBoard sudokuBoard = new SudokuBoard();
                SudokuBoardReader read = new SudokuBoardReader();
                read.loadBoardFromCSV(sudokuBoard.board);
                window1.dispose();
            }
        });

        JButton buttonExit = new JButton("Wyjście");
        buttonExit.setSize(300, 75);
        buttonExit.setLocation(100, 375);
        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Wyszedłeś z programu");
                System.exit(0);
            }
        });
        JButton buttonPrint = new JButton("Print");
        buttonPrint.setSize(300, 75);
        buttonPrint.setLocation(100, 275);
        buttonPrint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TablePrinter print = new TablePrinter();
                print.print();
                JOptionPane.showMessageDialog(null, "Plik 'Sudoku.txt' z tabelą został pomyślnie utworzony", "Print", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        window1.getContentPane().add(buttonNowaGra);
        window1.getContentPane().add(buttonImport);
        window1.getContentPane().add(buttonExit);
        window1.getContentPane().add(buttonPrint);

        window1.setVisible(true);
    }
}
