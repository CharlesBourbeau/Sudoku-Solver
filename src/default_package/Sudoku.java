package default_package;

import javax.swing.*;
import java.util.Arrays;

public class Sudoku {

    private int[][] gameGrid;

    public Sudoku() {
        gameGrid = new int[9][9];
        //fill the array with zeros
        for(int row = 0; row < 9; row++){
            Arrays.fill(gameGrid[row],0);
        }
    }

    public Sudoku(int[][] grid){
        this.gameGrid = grid;
    }

    public int[][] getGameGrid() {
        return this.gameGrid;
    }

    public void setGameGrid(int row, int col, int number) {
        this.getGameGrid()[row][col] = number;
    }

    public boolean backtrackingSolver(){
        // first find the first empty cell inside the grid, so a cell with a 0
        // the row and col found will be held inside the variables row and col
        // iterate through the columns first
        this.prettyPrint();

        int row = 0;
        int col = 0;
        boolean isFound = false;
        for(row = 0; row < 9; row++){
            for(col = 0; col < 9; col++){
                if(this.getGameGrid()[row][col] == 0){
                    isFound = true;
                    break;
                }

            }

            if(isFound) break;

        }

        // else if we didn't find an empty cell, return true, the puzzle is solved
        // base case
        if (row == 9 && col == 9){
            return true;
        }

        for(int number = 1; number <= 9; number++){
            // first do the 3 checks
            if(checkRowSafety(row, number) && checkColumnSafety(col, number) && checkSquareSafety(row, col, number)){
                // set the cell to the non-conflicted number and go on to the next empty cell
                setGameGrid(row, col, number);
                modifyLabel(row, col, number);
                if(backtrackingSolver()) return true;
            }
        }

        // if all digits have been tried and the base case has not been reached, clear the cell return false
        modifyLabel(row, col, 0);
        setGameGrid(row, col, 0);
        return false;

    }

    /**
     * Method to check if a column of the game grid contains a certain number
     * @param col
     * @param number
     * @return boolean isSafe
     */
    public boolean checkColumnSafety(int col, int number) {
        boolean isSafe = true;
        for(int i = 0; i < 9; i++){
            // check if the number at the current row inside the specified column is the same as the number to check
            if(this.getGameGrid()[i][col] == number){
                isSafe = false;
            }
        }
        return isSafe;
    }

    /**
     * Method to check if a row of the game grid contains a certain number
     * @param row
     * @param number
     * @return boolean isSafe
     */
    public boolean checkRowSafety(int row, int number) {
        boolean isSafe = true;
        for(int i = 0; i < 9; i++){
            // check if the number at the current col inside the specified row is the same as the number to check
            if(this.getGameGrid()[row][i] == number){
                isSafe = false;
            }
        }
        return isSafe;
    }

    /**
     * Method to check if a 3x3 square of the game grid contains a certain number
     * From the row number and the column number, both from 0 to 8, this method will return false if the
     * square contains the number.
     * @param row
     * @param col
     * @param number
     * @return boolean isSafe
     */
    public boolean checkSquareSafety(int row, int col, int number) {
        boolean isSafe = true;
        // the row and columns should be multiples of 3
        while(row % 3 != 0){
            row--;
        }
        while(col % 3 != 0){
            col--;
        }

        int rowLimit = row + 3;
        int colLimit = col + 3;

        for(int pRow = row; pRow < rowLimit; pRow++){
            for(int pCol = col; pCol < colLimit; pCol++){
                if(this.getGameGrid()[pRow][pCol] == number){
                    isSafe = false;
                }
            }
        }
        return isSafe;
    }

    public static boolean isInitialNumbers = true;
    // method to find the label inside the scene from a given row index and colum index and modify
    // its text with a number. If this number is 0, set the text to an empty string
    // labels are named label<row><col>
    // ex: label48 for row 4 and col 8
    public static void modifyLabel(int row, int col, int number){
        // we need to find the index of the labelArray inside the Main class.
        // 0,0 will yield index 0, 0,8 will yield index 8 , 1,0 index 9 and
        // 1,1 will yield index 10, hence row * 9 + col

        if(!isInitialNumbers){
            try{
                Thread.sleep(100);
            } catch(InterruptedException e){
                System.out.println(""+ e);
            }
        }


        int index = row * 9 + col;
        JLabel tempLabel = Main.labelArray[index];
        if(number == 0 ){
            tempLabel.setText("");
        } else {
            tempLabel.setText("" + number);
        }
    }


    private void print(String str){
        System.out.print(str);
    }
    public void prettyPrint(){
        print("\n\n");
        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                String myString = " " + gameGrid[row][col] + " ";
                if(col == 8) myString += "\n";
                print(myString);
            }
        }

    }

}










