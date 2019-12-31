package default_package;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main{

    public static JLabel[] labelArray = new JLabel[81];

    public static JButton solveButton;
    public static JFrame frame;

    public static boolean solveButtonClicked = false;

    static int[][] preMadeSudoku = {

            {0, 0, 0, 0, 7, 0, 0, 0, 0},
            {0, 0, 0, 5, 0, 0, 0, 9, 0},
            {0, 0, 0, 4, 0, 9, 0, 0, 8},
            {0, 1, 9, 0, 5, 0, 0, 8, 0},
            {2, 0, 0, 6, 1, 0, 7, 0, 9},
            {0, 0, 4, 0, 0, 0, 0, 0, 3},
            {0, 0, 0, 0, 6, 0, 0, 5, 2},
            {0, 8, 0, 7, 0, 0, 9, 0, 0},
            {0, 0, 3, 0, 8, 5, 6, 0, 0},

    };

    public static void main(String[] args) {

        int counter = 0;
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                JLabel newLabel = new JLabel();
                newLabel.setOpaque(true);
                newLabel.setBackground(Color.WHITE);
                newLabel.setText("");
                newLabel.setHorizontalAlignment(SwingConstants.CENTER);
                labelArray[counter] = newLabel;
                counter++;
            }
        }

        solveButton = new JButton("Solve!");
        solveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == solveButton){
                    System.out.println("Clicked solve!");
                    solveButtonClicked = true;
                }
            }
        });

        frame = new JFrame("Sudoku Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10,9, 5, 5));


        //Adding the square labels to the content pane
        for(int i = 0; i < 81; i++){
            frame.getContentPane().add(labelArray[i], BorderLayout.CENTER);
        }
        //Adding the solve button
        frame.getContentPane().add(solveButton, BorderLayout.CENTER);

        //Display the window
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setBounds(0,0,800,800);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);



        Sudoku.isInitialNumbers = true;

        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                if(preMadeSudoku[row][col] != 0){
                    Sudoku.modifyLabel(row, col, preMadeSudoku[row][col]);

                }
            }
        }

        Sudoku.isInitialNumbers = false;


        while(true){
            // bait print to stay inside the infinite loop
            System.out.println("");
            if(solveButtonClicked){
                // the asynchronous event of clicking the solve button is initiated here
                startSolver();
                solveButtonClicked = false;
            }
        }


    }

    public static void startSolver(){

        Sudoku mySudoku = new Sudoku(preMadeSudoku);
        mySudoku.backtrackingSolver();

        mySudoku.prettyPrint();
    }

}
