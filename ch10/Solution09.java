/* 
10.9 Sorted Matrix Search
	 Given an M x N matrix in which each row and each column is sorted in 
	 ascending order, write a method to find an element.
*/

import java.lang.*;
import java.io.*;
import java.util.*;

class Input {
	int[][] matrix;
	int target;

	public Input(int[][] matrix, int target) {
		this.matrix = matrix;
		this.target = target;
	}
}

class Output {
	int m;
	int n;

	public Output(int m, int n) {
		this.m = m;
		this.n = n;
	}
}

public class Solution09 {
	public static void main(String[] args) {
		System.out.println("10.9 Sorted Matrix Search");

		test();
	}

	private static void test() {
		Input input = generateInput();

		Output output = sortedMatrixSearch(input.matrix, input.target);

		System.out.println(output.m + " " + output.n);
	}

	private static Input generateInput() {
		int[][] matrix = {{11, 22, 33, 44}, 
						  {21, 32, 43, 54}, 
						  {31, 42, 53, 64}, 
						  {41, 52, 63, 74}};
		int target = 42;

		return new Input(matrix, target);
	}

	/* Solutions 
	Assumptions:
		If there are duplicates, return any position of the target
	
	Implementation:
		Since the right up corner is the biggest one in the row, the smallest
		one in the column. Compare target with it could eliminate 1 row/col
		every time. Same for left down corner.

		Time: O(m+n)
		Space: O(1)
	*/
	private static Output sortedMatrixSearch(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return new Output(-1, -1);

		int row = 0;
		int col = matrix[0].length-1; //start from right up corner
		while(row < matrix.length && col >= 0) {
			if (matrix[row][col] == target)
				return new Output(row, col);
			else if (target < matrix[row][col]) //not in the column
				col--;
			else //not in the row
				row++;
		}

		return new Output(-1, -1);
	}
}