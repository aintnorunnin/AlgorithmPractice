package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an 
 * n x n chessboard such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. 
You may return the answer in any order.

Each solution contains a distinct board configuration of 
the n-queens' placement, where 'Q' and '.' 
both indicate a queen and an empty space, respectively.
Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * @author Brandon
 *
 */
public class N_Queens {
	public List<List<String>> solveNQueens(int n) {
		List<List<String>> result = new ArrayList<>();
		char [][] board = new char[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(board[i], '.');
		}
        solveQueens(n, 0, board,result);
		return result;
    }
	
	private void solveQueens(int numQueens, int currentRow, char [][] board, List<List<String>> result) {
		//Base Case: Goal is to have placed all queens
		if (numQueens == currentRow) {
			result.add(convertBoard(board));
		}
		
		//Choose within constraints. 
		//Check each columns placemeant validity, if valid then move to next row
		for(int col = 0; col < numQueens; col++) {
			if(isValidMove(board, currentRow, col) ) { // Check
				board[currentRow][col] = 'Q'; //Choose
				solveQueens(numQueens, currentRow+1, board, result); //Explore next row
				board[currentRow][col] = '.'; //Unchoose
			}
		}	
	}
	

	private boolean isValidMove(char[][] board, int currentRow, int currentCol) {
		int rowAbove = currentRow - 1;
		int columnToRight = currentCol + 1;
		int columnToLeft = currentCol - 1;
		//Check if there are queens above this position.
		//No need to check below current row, because we haven't
		//placed any queens below it yet. 
		for (int i = rowAbove; i >= 0; i--) {
			if (board[i][currentCol] == 'Q') return false;
		}
		
		//Check diagonal to the right
		for (int i = rowAbove, j = columnToRight; i >=0 && j < board.length; i--, j++) {
			if (board[i][j] == 'Q') return false;
		}
		
		//Check diagonal to the left
		for (int i = rowAbove, j = columnToLeft; i >=0 && j >= 0; i--, j--) {
			if (board[i][j] == 'Q') return false;
		}
		return true;
	}
	
	private List<String> convertBoard(char[][] board) {
		List<String> placeMeants = new ArrayList<>();
		for(int i = 0; i < board.length; i++) {
			placeMeants.add(new String(board[i]));
		}
		return placeMeants;
	}

	
}
