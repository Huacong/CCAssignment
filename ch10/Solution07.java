/* 
10.7 Missing Int
	 Given an input file with four billion non-negative integers, provide an 
	 algorithm to generate an integer that is not contained in the file. Assume 
	 you have 1 GB of memory available for this task.

	 FOLLOW UP
	 What if you have only 10 MB of memory? Assume that all the values are 
	 distinct and we now have no more than one billion non-negative integers.
*/

import java.lang.*;
import java.io.*;
import java.util.*;

public class Solution07 {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("10.7 Missing Int");

		test();
	}

	private static void test() throws FileNotFoundException{
		String path = generateInput();

		missingInt1(path);
		//missingInt2(path);
	}

	private static String generateInput() {
		String path = "10.7_test.txt";
		return path;
	}

	/* Solution
	Analysis:
	   There are 2^31 unique non-negative integers, which is 2 billion, thus 
	   4 billion contains duplicates. 

	   1 GB = 2^33 bit, it is enough for bitvector for 2^31 numbers 
	   
	   Use bitvector to indicate which number has not appeared

	Time: O(n)
	Space: O(n)
	*/
	private static int missingInt1(String filename) throws FileNotFoundException{
		byte[] bitvector = new byte[2^28];//size of 2 billion = 2^31

		//set bitvector for all input number
		Scanner scanner = new Scanner(new FileReader(filename));
		while(scanner.hasNextInt()) {
			int num = scanner.nextInt();
			set(bitvector, num);
		}

		//check which bit is 0
		for (int i = 0; i < bitvector.length; i++) {
			for (int j = 0; j < 8; j++) {
				if (miss(bitvector[i], j))
					return i * 8 + j;
			}
		}

		return -1;
	}

	private static void set(byte[] bitvector, int num) {
		int index = 1 << num%8;

		bitvector[num/8] |= index;
	}

	private static boolean miss(byte b, int index) {
		return (b & (1 << index)) == 0;
	}

	/* Follow Up
	Analysis:
		Since all the integer are distinct, we can divide them into groups,
		count the number for each group and find out which group miss 1 int,
		finally use the method above to find out the miss int in that group.
		
		Suppose the group size is x
		There are at most 2^30 integers (1 million)
		There are 10 * 2^20 bytes memory
		We need 2^30 / x integers as counters, 2^30 / x * 4 <= 10 * 2^20
		We need x / 8 for bitvector

		We choose x = 2^10

		Time: O(n)
		Space: O(n/x)
	*/
	private static int missingInt2(String filename) throws FileNotFoundException {
		//find the group that miss int
		int groupSize = 2^10;
		int groupNum = 2^30 / groupSize;

		int[] groups = new int [groupNum];
		Scanner scanner = new Scanner(new FileReader(filename));
		while(scanner.hasNextInt()) {
			int num = scanner.nextInt();
			groups[num / groupNum]++; 
		}

		int targetGroup = 0; //group that miss int
		for (; targetGroup < groupNum && groups[groupNum] < groupSize; targetGroup++);
		
		//reread the whole file and set bit vector
		byte[] bitvector = new byte[groupSize/8];

		scanner = new Scanner(new FileReader(filename));
		while(scanner.hasNextInt()) {
			int num = scanner.nextInt();
			if (num / groupSize == targetGroup) {
				num %= groupSize;
				set(bitvector, num);
			}
		}

		//find the missing int
		for (int i = 0; i < groupSize/8; i++) {
			for (int j = 0; j < 8; j++)
				if (miss(bitvector[i], j))
					return targetGroup * groupSize + i * 8 + j;
		}
			
		return -1;
	}
}