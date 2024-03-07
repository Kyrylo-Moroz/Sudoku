import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

public class SudokuBoard extends JFrame {

    private static final int BOARD_SIZE = 9;
    public JTextField[][] board = new JTextField[BOARD_SIZE][BOARD_SIZE];

    public SudokuBoard() {

        setTitle("Sudoku Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);


        JButton button0 = new JButton("Podpowiedź");
        JButton button1 = new JButton("Zapisz plansze");
        JButton button2 = new JButton("Rozwiąż/sprawdź");
        JButton button3 = new JButton("Wyjdź");


        JPanel buttonPanel = new JPanel();

        buttonPanel.add(button0);
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        button0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Hint hint = new Hint(board, SudokuGenerator.getRemovedValues());
                hint.addHint();
            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Wyszedłeś z programu");
                System.exit(0);
            }
        });
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SudokuBoardSaver sudokuBoardSaver = new SudokuBoardSaver();
                sudokuBoardSaver.saveBoardToCSV(board);
                JOptionPane.showMessageDialog(null, "Zapisano planszę", "Zapis", JOptionPane.INFORMATION_MESSAGE);

            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Mistakes Mistakes = new Mistakes(board, SudokuGenerator.getRemovedValues());
                Mistakes.checkMistakes();
                boolean isBoardWhite = true;
                Map<int[], String> removedValues = SudokuGenerator.getRemovedValues();

                for (Map.Entry<int[], String> entry : removedValues.entrySet()) {
                    int[] key = entry.getKey();

                    int row = key[0];
                    int col = key[1];

                    if (!board[row][col].getBackground().equals(Color.WHITE)) {
                        isBoardWhite = false;
                        break;
                    }
                }

                if (isBoardWhite) {
                    SudokuBoardSolver solver = new SudokuBoardSolver(board);
                    solver.solver();
                    JOptionPane.showMessageDialog(null, "Sudoku zostało rozwiązane!", "Koniec", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Popraw błędy!", "Błędy", JOptionPane.INFORMATION_MESSAGE);
                }



            }
        });

        JPanel panel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new JTextField();
                board[i][j].setHorizontalAlignment(JTextField.CENTER);
                board[i][j].setFont(new Font("Arial", Font.PLAIN, 20));
                board[i][j].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        JTextField source = (JTextField) e.getSource();
                        String currentText = source.getText();
                        if (currentText.length() >= 1 || c < '1' || c > '9') {
                            e.consume(); // Ignoruj wprowadzenie nieprawidłowych znaków lub gdy pole jest już wypełnione
                        }
                    }
                });
                panel.add(board[i][j]);
            }
        }

        getContentPane().add(panel, BorderLayout.CENTER);

        setVisible(true);

    }

}

