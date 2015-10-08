/* 
5.4 Next Number:
	Given a positive integer, print the next smallest and the next largest number
	that have the same number of 1 bits in their binary representation. 
*/


import java.io.*;
import java.util.*;

class Result {
	public int nextSmallest = 0;
	public int nextLargest = 0;
}

class Index {
	public int left = -1;
	public int right = -1;
}

public class Solution04 {
	public static void main(String[] args) {
		System.out.println("5.4 Next Number");
		test();
	}

	private static void test() {
		String s = "0011001100";
		System.out.println("Original: 	" + s);

		int num = Integer.parseInt(s, 2);
		Result result = nextNumber(num);

		System.out.println("Next smallest:	" + Integer.toBinaryString(result.nextSmallest));
		System.out.println("Next largest:	" + Integer.toBinaryString(result.nextLargest));
		System.out.println();

		s = "0011000011";
		System.out.println("Original:	" + s);

		num = Integer.parseInt(s, 2);
		result = nextNumber(num);

		System.out.println("Next smallest:	" + Integer.toBinaryString(result.nextSmallest));
		System.out.println("Next largest:	" + Integer.toBinaryString(result.nextLargest));
		System.out.println();

		s = "0011";
		System.out.println("Original:	" + s);

		num = Integer.parseInt(s, 2);
		result = nextNumber(num);

		System.out.println("Next smallest:	" + Integer.toBinaryString(result.nextSmallest));
		System.out.println("Next largest:	" + Integer.toBinaryString(result.nextLargest));
		System.out.println();

		s = "10000000000000000000000000000000";
		System.out.println("Original:	" + s);

		num = Integer.MIN_VALUE;
		result = nextNumber(num);

		System.out.println("Next smallest:	" + Integer.toBinaryString(result.nextSmallest));
		System.out.println("Next largest:	" + Integer.toBinaryString(result.nextLargest));
		System.out.println();

		s = "0";
		System.out.println("Original:	" + s);

		num = Integer.parseInt(s, 2);
		result = nextNumber(num);

		System.out.println("Next smallest:	" + Integer.toBinaryString(result.nextSmallest));
		System.out.println("Next largest:	" + Integer.toBinaryString(result.nextLargest));
		System.out.println();

		s = "11111111111111111111111111111111";
		System.out.println("Original:	" + s);

		num = -1;
		result = nextNumber(num);

		System.out.println("Next smallest:	" + Integer.toBinaryString(result.nextSmallest));
		System.out.println("Next largest:	" + Integer.toBinaryString(result.nextLargest));
	}

	/* Solution
	Analysis:
	Next smallest number:
		Find the 1st most right ones block. If it is not start from index 0 (right
		to left), move its most right one 1 bit right 
			0011001100
			0011001010

		If not the above case, find the 2nd most right ones block. Move the most 
		right one of the 2nd block 1 bit right. Move the 1st block to left, right 
		after the one we just move from 2nd block
			0011000011
			0010111000
			

	Next largest number:
		Find the 1st ones block. Move its most left one 1 bit left, and all other
		one to the most right of the number 
			0011001100
			0011010001

	Assumptions:
		If no next smallest/largest, return 0
	
	Corner cases:
		All zeros (no smallest/largest)

		All ones (no smallest/largest)

		The 1st ones block is on the most right and only 1 ones block(no smallest)
			0000000011

		The 1st ones block is on the most left and only 1 ones block(no largest)
			1100000000

		Time: O(1)
		Space: O(1)
	*/
	private static Result nextNumber(int num) {
		Result result = new Result();

		if (num == -1 || num == 0)
			return result; //default -1

		result.nextSmallest = getNextSmallest(num);
		result.nextLargest = getNextLargest(num);

		return result;
	}

	private static int getNextSmallest(int num) {
		//find the first block and sencond block
		Index firstBlockIndex = getOnesBlockIndex(num, 0);
		Index secondBlockIndex = getOnesBlockIndex(num, firstBlockIndex.left + 1);

		//corner case:
		if (firstBlockIndex.right == -1 || //0
			//only 1 ones block and in the most right, already smallest, includes all 1
			(secondBlockIndex.right == -1 && firstBlockIndex.right == 0)) 
			return 0;

		//first block not at the rightmost, move the most right one 1 bit right
		int mask;
		if (firstBlockIndex.right != 0) {
			//clear most right one
			mask = ~(1 << firstBlockIndex.right);
			num &= mask;

			//set bit
			mask = ~mask >>> 1;
			num |= mask;

			return num;
		}

		//first block is at the rightmost

		//clear the all ones on the right of 2nd block
		mask = ~((1 << (secondBlockIndex.right + 1)) - 1);
		num &= mask;

		//move the most right one of 2nd block 1 bit right 
		//move the 1st block to left, right after 2nd block
		int size = firstBlockIndex.left - firstBlockIndex.right + 1;
		mask = (1 << (size+1)) - 1;
		mask = mask << (secondBlockIndex.right - 1 - size); //note the errors here
		num |= mask;

		return num;
	}

	private static int getNextLargest(int num) {
		//find the 1st block of ones
		Index firstBlockIndex = getOnesBlockIndex(num, 0);

		//corner case:
		if (firstBlockIndex.right == -1 || //0
			firstBlockIndex.left == 31) //already the largest, includes all 1
			return 0;

		int size = firstBlockIndex.left - firstBlockIndex.right + 1;
		
		//move the leftmost one 1 bit left
		int index = firstBlockIndex.left + 1;
		num |= (1 << index);

		//only 1 one, no need to move remaining ones
		if (size == 1)
			return num;

		//clear the right of pos
		int mask = ~((1 << index) - 1);
		num &= mask;

		//more than 1 one, move remaining ones
		mask = (1 << (size - 1)) - 1;
		num |= mask;

		return num;
	}

	//get ones block, return the left and right index
	private static Index getOnesBlockIndex(int num, int start) {
		Index result = new Index();

		for (; start < 32 ; start++) {
			if ((num & (1 << start)) != 0) {
				if (result.right == -1)
					result.right = start;
			} else { //zero bit, its right index is the left of the ones block
				if (result.right != -1) {
					result.left = start - 1;
					break;
				}
			}
		}

		//corner case, the most significant bit is 1
		if (start == 32) {
			if ((num & (1 << 31)) != 0)
				result.left = start - 1;
		}

		return result;
	}
}