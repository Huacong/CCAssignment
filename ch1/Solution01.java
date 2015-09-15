/* 
1.1 Is Unique: 
	Implement an algorithm to determine if a string has all unique characters.
	What if you cannot use additional data structures?

	Here we assume that "no additional data structures" means O(1) space 
	complexity.
*/

/*
Edge case: null, ""
*/

import java.io.*;
import java.util.*;

public class Solution01 {
	static public void main(String[] args) throws IOException {
		String s = input();

		//algorithm
		boolean b = isUnique1(s);
		//boolean b = isUnique2(s);
		//boolean b = isUnique3(s);

		output(b);
	}

	/* Helpers */
	static private String input() throws IOException {
		System.out.println("1.1 Is Unique");
		System.out.print("Please input the string: ");

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();

		return s;
	}

	static private void output(boolean b) {
		System.out.println(b);
	}

	/* Solutions */
	/*	Solution 1
	
		Assumption: Only have basic ASCII chars (0-127)

	   	Use bit vector to check if the chars already exists.

	   	Time: O(n)
	   	Space: O(1)
	*/
	static private boolean isUnique1(String s) {
		if (s == null || s.length() == 0)
			return true;

		boolean[] exists = new boolean[128]; //default values are false

		// iterate all chars of the string
		for (int i = 0; i < s.length(); i++) {
			if (exists[s.charAt(i)]) //exists
				return false;
			else
				exists[s.charAt(i)] = true;
		}

		return true;
	}

	/*	Solution 2

		Use hash set to check if the chars already exists, not specified
		to ASCII character set

		Time: O(n), if get and put of hash set are O(1) (get and put could 
			  be O(logn))
		Space: O(1), related to the size of the character set. Char in java
			   is 16-bit, thus no more than 65535 chars
	*/
	static private boolean isUnique2(String s) {
		if (s == null || s.length() == 0)
			return true;

		Set<Character> set = new HashSet<Character>();

		// iterate all chars of the string
		for (int i = 0; i < s.length(); i++) {
			if (set.contains(s.charAt(i))) //exists
				return false;
			else
				set.add(s.charAt(i));
		}

		return true;
	}

	/*	Solution 3

		Sort the string and check if there are two consecutive chars

		Time: O(nlogn), O(nlogn) for sort, O(n) for check
		Space: O(1), not consider the char array created by s.toCharArray()
	*/
	static private boolean isUnique3(String s) {
		if (s == null || s.length() == 0)
			return true;

		char[] chars = s.toCharArray();
		Arrays.sort(chars);

		// iterate all chars of the string
		for (int i = 1; i < chars.length; i++) {
			if (chars[i] == chars[i-1])
				return false;
		}

		return true;
	}
}