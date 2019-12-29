package default_package;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import javax.swing.*;

public class Main {

    public static JLabel[] labelArray = new JLabel[81];

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

        JFrame frame = new JFrame("Sudoku Solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(9,9, 5, 5));



        JLabel textLabel = new JLabel("I'm a label", SwingConstants.CENTER);
        textLabel.setPreferredSize(new Dimension(300, 100));
        for(int i = 0; i < 81; i++){
            frame.getContentPane().add(labelArray[i], BorderLayout.CENTER);
        }


        //Display the window
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setBounds(0,0,800,800);
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);

        startSolver();

    }

    public static void startSolver(){

        int[][] preMadeSudoku = {

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

        Sudoku.isInitialNumbers = true;

        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                if(preMadeSudoku[row][col] != 0){
                    Sudoku.modifyLabel(row, col, preMadeSudoku[row][col]);

                }
            }
        }

        Sudoku.isInitialNumbers = false;

        try{
            Thread.sleep(10000);
        } catch(InterruptedException e){
            System.out.println(""+ e);
        }


        Sudoku mySudoku = new Sudoku(preMadeSudoku);
        mySudoku.backtrackingSolver();

        mySudoku.prettyPrint();
    }
}
