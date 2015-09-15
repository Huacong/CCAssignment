/*
1.8 Zero Matrix:
	Write an algorithm such that if an element in an M*N matrix is 0, its entire
	row and column are set to 0.
*/


/*
Edge case: null, empty, [[1, 1], [1, 0]], [[1, 0], [1, 1]], [[1, 1], [0, 1]], [[0, 1], [1, 1]]
*/

import java.io.*;
import java.util.*;

public class Solution08 {
	static public void main(String[] args) throws IOException {
		int[][] matrix = input();

		zeroMatrix(matrix);		

		output(matrix);
	}

	/* Helpers */
	static private int[][] input() throws IOException {
		System.out.println("1.8 Zero Matrix");

		//read the dimension of the matrix
		System.out.print("Please input the dimension of the matrix (M N): ");

		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();

		//read the matrix
		System.out.println("Please input the matrix row by row, elements of the matrix are integer: ");

		int[][] matrix = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++)
				matrix[i][j] = sc.nextInt();
		}
		sc.close();

		return matrix;
	}

	static private void output(int[][] matrix) {
		System.out.println();
		System.out.println("After processing");

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	/* Solutions */
	/*	Solution 1
		
		Use the first row (1 to n-1) to indicate whether the associated row should be set to 0
		Use the first col (1 to n-1) to indicate whether the associated col should be set to 0
		Use additional variable to indicate whether the 1st row/col should be set to 0

		Time: O(mn)
		Space: O(1)	
	*/
	static private void zeroMatrix(int[][] matrix) {
		if (matrix == null)
			return;

		//use 1st row/col to indicate whether the row/col should be set to 0
		int m = matrix.length;
		int n = matrix[0].length;
		boolean rowHeadZero = false;
		boolean colHeadZero = false;

		//indicator for row head
		for (int i = 0; i < m; i++) {
			if (matrix[i][0] == 0) {
				rowHeadZero = true;
				break;
			}
		}

		//indicator for col head
		for (int i = 0; i < n; i++) {
			if (matrix[0][i] == 0) {
				colHeadZero = true;
				break;
			}
		}

		//scan matrix
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][0] = 0;
					matrix[0][j] = 0;
				}
			}
		}

		//set row zeroes
		for (int i = 1; i < m; i++) {
			if (matrix[i][0] == 0) {
				for (int j = 1; j < n; j++)
					matrix[i][j] = 0;
			}
		}

		//set col zeroes
		for (int j = 1; j < n; j++) {
			if (matrix[0][j] == 0) {
				for (int i = 1; i < m; i++)
					matrix[i][j] = 0;
			}
		}

		//set row/col head
		if (rowHeadZero)
            for (int i = 0; i < m; i++)
                matrix[i][0] = 0;
        
        if (colHeadZero)   
            for (int j = 0; j < n; j++)
                matrix[0][j] = 0;
	}
}