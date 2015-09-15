/*
1.6 String Compression:
	Implement a method to perform basic string compression using the counts of 
	repeated chracters. For example, the string aabcccccaaa would become 
	a2b1c5a3. If the "compressed" string would not bcome smaller than the original
	string, your method should return the original string. You can assume the 
	string has only uppercase and lowercase letters (a-z).
*/

/*
Edge case: null, "", 
*/

import java.io.*;
import java.util.*;

public class Solution06 {
	static public void main(String[] args) throws IOException {
		String s = input();

		String result = stringCompression(s);

		output(result);
	}

	/* Helpers */
	static private String input() throws IOException {
		System.out.println("1.6 String Compression");
		System.out.print("Please input the string to be compressed: ");

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();

		return s;
	}

	static private void output(String s) {
		System.out.println(s);
	}

	/* Solutions */
	/*	Solution 1
		
		Strightly counting each consecutive char, and append the counts to the new
		string.
	*/
	static private String stringCompression(String s) {
		if (s == null || s.length() == 0)
			return s;

		//count and append
		StringBuilder result = new StringBuilder();
		int count = 1;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == s.charAt(i-1)) {
				count++;
			} else {
				result.append(s.charAt(i-1));
				result.append(count);
				count = 1;

				if (result.length() >= s.length())
					return s;
			}
		}

		//append the last char
		result.append(s.charAt(s.length() - 1));
		result.append(count);

		if (result.length() >= s.length())
			return s;

		return result.toString();
	}
}