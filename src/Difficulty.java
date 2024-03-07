import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Difficulty extends JFrame {
    public Difficulty() {
        setTitle("Wybierz poziom trudności");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLayout(null);

        JButton buttonlatwy = new JButton("Łatwy");
        buttonlatwy.setSize(300, 75);
        buttonlatwy.setLocation(100, 100);
        buttonlatwy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SudokuBoard sudokuBoard = new SudokuBoard();
                SudokuGenerator.generateBoard(sudokuBoard, "łatwy");
            }
        });

        JButton buttonsredni = new JButton("Średni");
        buttonsredni.setSize(300, 75);
        buttonsredni.setLocation(100, 200);
        buttonsredni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SudokuBoard sudokuBoard = new SudokuBoard();
                SudokuGenerator.generateBoard(sudokuBoard,  "średni");
            }
        });

        JButton buttontrudny = new JButton("Trudny");
        buttontrudny.setSize(300, 75);
        buttontrudny.setLocation(100, 300);
        buttontrudny.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                SudokuBoard sudokuBoard = new SudokuBoard();
                SudokuGenerator.generateBoard(sudokuBoard, "trudny");
            }
        });

        getContentPane().add(buttonlatwy);
        getContentPane().add(buttonsredni);
        getContentPane().add(buttontrudny);
    }
    public void showWindow() {
        setVisible(true);
    }
}
