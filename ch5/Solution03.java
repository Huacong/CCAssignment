/* 
5.3 Flip Bit to Win:
	You have an integer and you can flip exactly one bit from a 0 to a 1. Write 
	code to find the length of the longest sequence of 1s you could create.

	EXAMPLE
	Input: 1775 (or: 11011101111)  
	Output: 8
*/

/* Corner cases: all zeros or all ones. */

import java.io.*;
import java.util.*;
import java.lang.*;

class Result {
	public boolean moreThanOneZeros = false;
	public int continueOnes = 0;
	public int nextIndex = -1;
}

public class Solution03 {
	public static void main(String[] args) {
		System.out.println("5.3 Flip Bit to Win");
		test();
	}

	private static void test() {
		int num = 1775;
		int result = flipBitToWin(num);
		System.out.println(result);

		num = 0;
		result = flipBitToWin(num);
		System.out.println(result);

		num = -1;
		result = flipBitToWin(num);
		System.out.println(result);
	}

	/* Solution

		Find out the longest consecutive 1 blocks with at most 1 zero between 
		them
	
		Time: O(1)
		Space: O(1)
	*/
	private static int flipBitToWin(int num) {
		if (num == -1)
			return 32;

		int max = 0;
		int pre = 0; //counter for pre 1 block
		int cur = 0; //counter for cur 1 block

		for (int i = 0; i < 32; i++) {
			int mask = 1 << i;

			if ((num & mask) != 0) {//current bit is 1
				cur++;
			} else { //current bit is 0
				if (cur != 0)
					max = Math.max(max, pre + cur + 1);

				pre = cur;
				cur = 0;
			}
		}

		if (cur != 0) //in case no zero after the last 1 block
			max = Math.max(max, pre + cur + 1);

		return max;
	}
}