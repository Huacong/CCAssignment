/*
1.9 String Rotation:
	Assume you have a method isSubstring which checks if one word is a substring 
	of another. Given two strings, s1 and s2, write code to check if s2 is a 
	rotation of s1 using only one call to isSubstring (e.g., "waterbottle" is a 
	rotation of "erbottlewa").
*/

/*
Edge case: null, s1.length() != s2.length()
*/

import java.io.*;
import java.util.*;

public class Solution09 {
	static public void main(String[] args) throws IOException {
		List<String> list = input();

		boolean b = stringRotation(list.get(0), list.get(1));

		output(b);
	}

	/* Helpers */
	static private List<String> input() throws IOException {
		System.out.println("1.9 String Rotation");
		System.out.print("Please input strings (separate by space. E.g., waterbottle erbottlewat): ");

		Scanner sc = new Scanner(System.in);
		List<String> list = new ArrayList<String>(2);
		list.add(sc.next());
		list.add(sc.next());
		sc.close();

		return list;
	}

	static private void output(boolean b) {
		System.out.println(b);
	}

	/* Solutions */
	/*	Solution 1

		Concat s1 twice and check if s2 is the substring of the new string. The part inside the new
		string is what right rotation actually happen.
		
		Time: O(n), O(n) for concat, and O(n) for checking substring (kmp or other similar algorithm)
		Space: O(n), Create a new string that concat s1 twice
	*/
	static private boolean stringRotation(String s1, String s2) {
		if (s1 == null || s2 == null)
			return s1 == s2;

		if (s1.length() != s2.length())
			return false;

		//concat s1
		s1 = s1 + s1;

		//check if s2 is the substring of s1
		return s1.indexOf(s2) != -1;
	}
}