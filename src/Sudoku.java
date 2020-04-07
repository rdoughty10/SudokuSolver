import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoku {
	
	
	public Sudoku(int[][] brd) {
		
		System.out.println("Original Board:");
		printAnswer(brd);
		
		if (solve(brd)) {
			System.out.println("\n\nCompleted Board:");
			printAnswer(brd);
		}
		else {
			System.out.println("\nThere is no solution");
		}
		
	}
	
	private boolean solve(int[][] brd) {
		// recrusive function that
		int row = -1;
		int col = -1;
		boolean isEmpty = true;
		
		for (int i = 0; i < brd.length; i++) {
			for (int j = 0; j < brd.length; j++) {
				if (brd[i][j] == 0) {
					row = i;
					col = j;
					
					isEmpty = false;
					break;
				}
			}
			
			if (!isEmpty) break;
		}
		
		if(isEmpty) {
			return true;
		}
		
		for (int i = 1; i <= 9; i++) {
			if(isValid(brd, row, col, i)) {
				brd[row][col] = i;
				if(solve(brd)) {
					return true;
				}else {
					brd[row][col] = 0;
				}
			}
		}
		
		return false;
		
	}
	
	private boolean isValid(int[][] brd, int row, int col, int num) {
		//returns if the number passed is a valid solution at the row,column location of brd
		boolean isValid = true;
		for(int i = 0; i < brd.length; i++) {
			if (brd[i][col]==num) {
				isValid = false;
			}
			if (brd[row][i]==num) {
				isValid = false;
			}
		}
		
		
	    int sqrt = (int) Math.sqrt(brd.length); 
	    int boxRowStart = row - row % sqrt; 
	    int boxColStart = col - col % sqrt; 
	  
	    for (int r = boxRowStart; 
	             r < boxRowStart + sqrt; r++)  
	    { 
	        for (int d = boxColStart;  
	                 d < boxColStart + sqrt; d++)  
	        { 
	            if (brd[r][d] == num)  
	            { 
	                return false; 
	            } 
	        } 
	    } 
		
		
		
		return isValid;
	}

	private void printAnswer(int[][] brd) {
		for(int i = 0; i < brd.length; i++) {
			System.out.println("");
			for (int j = 0; j < brd.length; j++) {
				System.out.print(brd[i][j] + " ");
			}
			
		}
		
	}
	
	private static int[][] getInput(String filename) throws FileNotFoundException {
		int[][] brd = new int[9][9];
		File file = new File(filename);
		Scanner input = new Scanner(file);
		for(int i = 0; i<9; i++) {
			for(int j = 0; j<brd.length; j++) {
				int num = input.nextInt();
				brd[i][j] = num;
			}
		}
		
		return brd;
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		
		int[][] brd = getInput("input.txt");	
		Sudoku sudoku = new Sudoku(brd);

	}


}
