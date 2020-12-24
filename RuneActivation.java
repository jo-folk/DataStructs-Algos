
// This program solves a given sudoku puzzle

import java.util.*;

// The class for the rune activation
public class RuneActivation{

    // Our sudoku grid that we will modify
    public static char[][] initial_grid;
    public static char[][] spells;    
    public static char[][] final_state;
    // The length and height of the sudoku grid
    public static int row,col;

    // The main method
    public static void main(String[] Args) {

        // We will read in the puzzle through standard input
        Scanner sc = new Scanner(System.in);

        // Read in the dimensions of the board
        row = sc.nextInt();
        col = sc.nextInt();

        String str=sc.nextLine();

        // Create the board
        initial_grid = new char[row][col];
        final_state = new char[row][col];
        spells = new char[row][col];

        // Read in the full board
        for (int i = 0; i < row; i++) {
            str=sc.nextLine();
            // System.out.println("read"+str);
            for (int j = 0; j < col; j++) {
                initial_grid[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < row; i++) {
            str=sc.nextLine();
            // System.out.println(str);
            for (int j = 0; j < col; j++) {
                final_state[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                spells[i][j] = '-';
            }
        }


        // Try to solve the puzzle!
        if (solve(0, 0)) {

            // We found a solution! Now print it.
            printGrid();
        } else {

            // Puzzle could not be solved T_T
            System.out.println("-1");
            // printGrid();
        }
    }

    // The method to print our grid
    public static void printGrid() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(spells[i][j]);
            }
            System.out.print('\n');
        }
        
    }



    // method to flip the state of cell r,c
    public static void flip(int r, int c){
        if(initial_grid[r][c]=='A'){
            initial_grid[r][c]='D';

        }
        else if(initial_grid[r][c]=='D'){
            initial_grid[r][c]='A';
            
        }

    }

    //the method to cast spell 
    public static void castSpell(int r, int c) {
        flip(r,c);
        if(r>0){
            flip(r-1,c);
        }
        if(r<row-1){
            flip(r+1,c);
        }
        if(c>0){
            flip(r,c-1);
        }
        if(c<col-1){
            flip(r,c+1);
        }
        
    }

    // checking if valid state
    public static boolean valid(int r, int c) {

        if (r>0){
          if( initial_grid[r-1][c]!=final_state[r-1][c]){
              return false;
          }
        }

        // if last row
        if(r==row-1){
            // if last column
            if(c==col-1){
                if( initial_grid[r][c]!=final_state[r][c]){
                    return false;
                }
            }

            if(c>0){
                if( initial_grid[r][c-1]!=final_state[r][c-1]){
                    return false;
                }       
            }
  
          }

        return true;

    }

    // The method to solve the sudoku puzzle
    public static boolean solve(int r, int c) {

        if (r == row) return true;
        if (c == col ) return solve(r + 1, 0);

        // trying without any spell cast
        if (valid(r, c) && solve(r, c + 1)) return true;


        // trying with spell cast
        castSpell(r,c);
        spells[r][c]='C';
        if (valid(r, c) && solve(r, c + 1)) return true;
        // back tracking
        castSpell(r,c);
        spells[r][c]='-';
        return false;
    }
}
