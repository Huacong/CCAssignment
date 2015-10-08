/* 
5.7 Pairwise Swap: 
	Wtire a program to swap odd and even bits in an integer with as few instructions
	as possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and
	so on). 
*/


import java.io.*;
import java.util.*;

public class Solution07 {
	public static void main(String[] args) {
		System.out.println("5.7 Pairwise Swap");
		test();
	}

	private static void test() {
		int num = 170;

		int result = pairwiseSwap(num);
		System.out.println(Integer.toBinaryString(num));
		System.out.println(Integer.toBinaryString(result));
	}
	/* Solution 

		Use masks to extract odd and even bits. Shift and merge
		
		Time: O(1)
		Space: O(1)
	*/
	private static int pairwiseSwap(int num) {
		int oddBits = num & 0x55555555; //0x5 = 0101
		int evenBits = num & 0xaaaaaaaa; //0xa = 1010

		return ((oddBits << 1) | (evenBits >>> 1));
	}
}