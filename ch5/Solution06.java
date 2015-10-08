/* 
5.6 Conversion: 
	Write a function to determine the number of bits you would need to flip to
	convert integer A to integer B.
	EXAMPLE
	Input: 29 (or: 11101), 15 (or: 01111)
	Output: 2 
*/

import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution06 {
	public static void main(String[] args) {
		System.out.println("5.6 Conversion");
		test();
	}

	private static void test() {
		int a = 29;
		int b = 15;

		int result = Conversion(a, b);
		System.out.println(result);
	}

	/* Solution 

		Count how many bits are different between two numbers.
		a XOR b, then count the number of bit 1.

		Time: O(1)
		Space: O(1)
	*/
	private static int Conversion(int a, int b) {
		int count = 0;
		int diff = a ^ b;

		//count bits after diff
		for (int i = 0; i < 32; i++) {
			if ((diff & (1 << i)) != 0)
				count++;
		}

		return count;
	}
}