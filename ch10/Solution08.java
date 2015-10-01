/* 
10.8 Find Dupliates
	 You have an array with all the numbers from 1 to N, where N is at most 
	 32,000. The array may have duplicate entries and you do not know what 
	 N is. With only 4 kilobytes of memory available, how would you print 
	 all duplicate elements in the array.
*/

/*
Edge case: 
*/

import java.io.*;
import java.util.*;

public class Solution08 {
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("10.8 Find Duplicates");

		test();
	}

	private static void test() throws FileNotFoundException{
		String path = generateInput();

		findDuplicates(path);
	}

	private static String generateInput() {
		String path = "10.8_test.txt";
		return path;
	}

	/* Solutions 
	Analysis:
		Memory 4 KB = 4 * 2^10 * 8 bit = 32 * 1024 bit > 32000.
		Thus bit vector can be used to indicate if a number have appeared.

	Assumptions:
		The rest of the memmory will be enough for running the program.

		The duplicate elements will be printed in the order of appearance, the 
		first appearance of the duplicate elements will not be printed

		Time: O(n)
		Space: O(n)
	*/
	private static void findDuplicates(String path) throws FileNotFoundException {
		byte[] bitvector = new byte[32000 / 8];//bit vector, 4000 bytes

		System.out.println("Duplicate numbers: ");

		Scanner scanner = new Scanner(new FileReader(path));
		while(scanner.hasNextInt()) {
			int num = scanner.nextInt();
			
			if (!exists(bitvector, num)) //check if exists
				set(bitvector, num); //set bit vector
			else
				System.out.print(num + " ");
		}

		System.out.println();
	}

	private static boolean exists(byte[] bitvector, int num) {
		byte group = bitvector[num/8];
		int index = 1 << num%8;

		return (group & index) != 0;
	}

	private static void set(byte[] bitvector, int num) {
		int index = 1 << num%8;

		bitvector[num/8] |= index;
	}
}