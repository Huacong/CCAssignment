/*
1.4 Palindrome Permutation:
	Given a string, write a function to check if it is a permutation of a 
	palindrome. A palindrome is a word or phrase that is the same forwards 
	and backwards. A permutation is a rearrangement of letters. The palindrome 
	does not need to be limited to just dictionary words.

	EXAMPLE
	Input:	Tact Coa
	Output:	True (permutations: "taco cat", "atco cta", etc)
*/

/*
Edge case: null, ""
*/

import java.io.*;
import java.util.*;

public class Solution04 {
	static public void main(String[] args) throws IOException {
		String s = input();

		//algorithm
		//boolean b = palindromePermutation1(s);
		boolean b = palindromePermutation2(s);

		output(b);
	}

	/* Helpers */
	//no exception handling
	static private String input() throws IOException {
		System.out.println("1.4 Palindrome Permutation");
		System.out.print("Please input the string: ");

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();

		return s;
	}

	static private void output(boolean b) {
		System.out.println(b);
	}

	/* Solutions */
	/*
		Assumption: All the character in string are English alphabet (a-z, A-Z); 
					Ignore case and spaces

		Analysis:	Palindrome string has at most 1 character appear odd times
					(i.e. <=1 char), all other chars should appear even times
	*/
					
	/*	Solution 1

	   	Use int array to count the appear times of each array

	   	Time: O(n)
	   	Space: O(1)
	*/
	static private boolean palindromePermutation1(String s) {
		if (s == null || s.length() == 0)
			return true;

		int[] times = new int[26];
		int oddChar = 0;

		for (int i = 0; i < s.length(); i++) {
			// convert char to index 0-25, ignore case
			char c = s.charAt(i);
			int index = 0;

			if (c >= 'a' && c <= 'z')
				index = c - 'a';
			else if (c >= 'A' && c <='Z')
				index = c - 'A';
			else //invalid char
				return false;

			//count appear times and num of odd chars
			times[index]++;
			if (times[index] % 2 == 0) //even
				oddChar--;
			else //odd
				oddChar++;
		}

		return oddChar <= 1;
	}

	/*	Solution 2

		Use bit vector to record the appear times of each letter, 1 for odd times
		and 0 for even times. Flip the bit everytime the associated char is met.
		Finally, if the bit vector has at most 1 bit which is set to 1, than it's
		a palindrome permutation.
		
		Time: O(n)
		Space: O(1), less than Solution 1
	*/
	static private boolean palindromePermutation2(String s) {
		if (s == null || s.length() == 0)
			return true;

		int bitVec = 0;

		for (int i = 0; i < s.length(); i++) {
			// convert char to index 0-25, ignore case
			char c = s.charAt(i);
			int index = 0;

			if (c >= 'a' && c <= 'z')
				index = c - 'a';
			else if (c >= 'A' && c <='Z')
				index = c - 'A';
			else //invalid char
				return false;

			//flip the bit indicated by index
			int mask = 1 << index;
			bitVec ^= mask;
		}

		if (bitVec == 0) //0 char appear odd times
			return true;
		else //1 char appear odd times
			//an efficient way to check if there is only one bit is 1
			return (bitVec & (bitVec - 1)) == 0;
	}
}