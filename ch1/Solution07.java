/*
1.7 Rotate Matrix:
	Given an image represented by an N*N matrix, where each pixel in the image 
	is 4 bytes, write a method to rotate the image by 90 degrees. Can you do 
	this in place?
*/

/*
Edge case: null, 0*0, 1*1
*/

import java.io.*;
import java.util.*;

public class Solution07 {
	static public void main(String[] args) throws IOException {
		int[][] image = input();

		rotateMatrix(image);		

		output(image);
	}

	/* Helpers */
	static private int[][] input() throws IOException {
		System.out.println("1.7 Rotate Matrix");

		//read the dimension of the matrix
		System.out.print("Please input the dimension of the matrix (one number): ");

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();
		int n = Integer.valueOf(s);

		//read the matrix
		System.out.println("Please input the matrix row by row, elements of the matrix are integer: ");

		Scanner sc = new Scanner(System.in);
		int[][] image = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				image[i][j] = sc.nextInt();
		}
		sc.close();

		return image;
	}

	static private void output(int[][] image) {
		System.out.println();
		System.out.println("After rotation");

		for (int i = 0; i < image.length; i++) {
			for (int j = 0; j < image.length; j++) {
				System.out.print(image[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	/* Solutions */
	/*	Solution 1

		Find out the mapping of each coordinate in different quadrants, and swap 
		the value in place

		Time: O(N^2)
		Space: O(1)
	*/
	static private void rotateMatrix(int[][] image) {
		if (image == null || image.length == 0)
			return;

		int n = image.length;
		int haxis = n / 2;
		int vaxis = (n - 1) / 2 + 1; //round up, handle N in odd or even

		for (int i = 0; i < haxis; i++) {
			for (int j = 0; j < vaxis; j++) {
				int temp = image[i][j];
				image[i][j] = image[n-1-j][i]; //4th quadrant -> 1st quadrant
				image[n-1-j][i] = image[n-1-i][n-1-j]; //3rd -> 4th
				image[n-1-i][n-1-j] = image[j][n-1-i]; //2nd -> 3rd
				image[j][n-1-i] = temp; //1st -> 2nd
			}
		}
	}
}