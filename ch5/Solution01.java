/* 
5.1 Insertion:
	You are given two 32-bit numbers, N and M, and two bit positions, i and j. 
	Write a method to insert M into N such that M starts at bit j and ends at
	bit i. You can assume that the bits j through i have enough space to all
	of M. That is, if M = 10011, you can assume that there are at least 5 bits
	between j and i. You would not, for example, have j = 3 and i = 2, because 
	M could not fully fit between bit 3 and bit 2.
	EXAMPLE
	Input:  N = 10000000000, M = 10011, i = 2, j = 6
	Output: N = 10001001100
*/


import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution01 {
	public static void main(String[] args) {
		System.out.println("5.1 Insertion");
		test();
	}

	private static void test() {
		String bin_n = "10000000000";
		String bin_m = "10011";
		int i = 2;
		int j = 6;

		int dec_n = Integer.parseInt(bin_n, 2);
		int dec_m = Integer.parseInt(bin_m, 2);

		int num = Insertion(dec_n, dec_m, i, j);
		
		System.out.println("N = " + bin_n);
		System.out.println("M = " + bin_m);
		System.out.println("R = " + Integer.toBinaryString(num));
	}

	/* Solution */
	/* Solution

		First clear the bits from i to j of N. 
		Then insert M to N.

		Time: O(1), 32 bits
		Space: O(1)
	*/
	private static int Insertion(int n, int m, int i, int j) {
		//create mask
		int ones = ~0; //0xFFFFFFFF
		int left = ones << (j+1); //set bits > j to 1
		int right = (1 << i) - 1; //set bits < i to 1
		int mask = left | right; //bits between i, j are 0

		//clear N
		n &= mask;

		//shift M
		m = m << i;

		return m | n;
	}
}