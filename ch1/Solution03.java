/*
1.3 URLify: 
	Write a method to replace all spaces in a string with '%20'. You may assume 
	that the string has sufficient space at the end to hold the additional 
	characters, and that you are given the "true" length of the string. 
	(Note: If implementing in Java, please use a character array so that you can
	perform this operation in place.)

	EXAMPLE
	Input: "Mr John Smith    ", 13
	Ouput: "Mr%20John%20Smith"
*/

/*
Edge case: null, ""
*/

import java.io.*;
import java.util.*;

public class Solution03 {
	static public void main(String[] args) throws IOException {
		String s = input();
		char[] chars = convert(s);

		//algorithm
		URLify(chars, s.length());

		output(chars);
	}

	/* Helpers */
	//no exception handling
	static private String input() throws IOException {
		System.out.println("1.3 URLify");
		System.out.print("Please input the string to be URLified: ");

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();

		return s;
	}

	static private void output(char[] chars) {
		System.out.println(chars);
	}

	//convert the input to required format
	static private char[] convert(String s) {
		//count the number of spaces
		int count = 0;
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == ' ')
				count++;

		char[] chars = new char[s.length() + 2 * count];

		//copy s to array
		s.getChars(0, s.length(), chars, 0);

		return chars;
	}

	/* Solutions */
	/*	Solution 1
		
		Assumption: chars.length is the final length, and >= len.
					If chars.length is not the final length, simply copy the code for
					couting spaces in input() here to compute the final size needed

	   	Modify the char array from end to the begining, in place

	   	Time: O(n)
	   	Space: O(1)
	*/
	static private void URLify(char[] chars, int len) {
		if (chars == null || chars.length == 0)
			return;

		//modify backward
		for (int i = len - 1, j = chars.length - 1; i >= 0, j >= 0; i--, j--) {
			//i is the index for real string
			//j is the index for the modified string, check j >= 0 to handle invalid 
			//initial size of chars
			if (chars[i] == ' ') {
				chars[j] = '0';
				j--;

				chars[j] = '2';
				j--;

				chars[j] = '%';
			} else {
				chars[j] = chars[i];
			}
		}
	}
}