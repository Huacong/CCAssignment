/*
1.5 One Away:
	There are three types of edits that can be performed on strings: insert a 
	character, remove a character, or replace a character. Given two strings, 
	write a function to check if they are one edit (or zero edits) away.

	EXAMPLE
	pale,  ple	->	true
	pales, pale	->	true
	pale,  bale	->	true
	pale,  bake	->	false
*/

/*
Edge case: null, s1.length() - s2.length() > 1
*/

import java.io.*;
import java.util.*;
import java.lang.Math;

public class Solution05 {
	static public void main(String[] args) throws IOException {
		List<String> list = input();

		//algorithm
		boolean b = oneWay1(list.get(0), list.get(1));
		//boolean b = oneWay2(list.get(0), list.get(1));
		//boolean b = oneWay3(list.get(0), list.get(1));

		output(b);
	}

	/* Helpers */
	//no exception handling
	static private List<String> input() throws IOException {
		System.out.println("1.5 One way");
		System.out.print("Please input strings (separate by space. E.g., pale ple): ");

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();

		Scanner sc = new Scanner(s);
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
		
		Use dynamic programing to compute the minimum number of steps to convert
		s1 to s2

		Time: O(n^2), because the difference of length of s1 and s2 are only allowed 
			  to be 1, which should be n and n-1. 
		Space: O(n^2), same as above
	*/
	static private boolean oneWay1(String s1, String s2) {
		if (s1 == null || s2 == null)
			return s1 == s2;

		int m = s1.length();
		int n = s2.length();

		if (Math.abs(m - n) > 1) //distance greater than 1
			return false;

		//step[i][j] is the minimum num of steps from s1[0, i-1] to s2[0, j-1]
		int[][] steps = new int[m+1][n+1];

		//s2 is empty, insert a char every time to become s1
		for (int i = 0; i <= m; i++)
			steps[i][0] = i;

		//s1 is empty
		for (int i = 0; i <= n; i++)
			steps[0][i] = i;

		//get minimum steps
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (s1.charAt(i-1) == s2.charAt(j-1))
					//current chars match, step same as stems[i-1][j-1]
					steps[i][j] = steps[i-1][j-1];
				else
					//steps[i-1][j] + 1, delete last char in s1, plus steps s1[0...i-2] -> s2[0...j-1]
                    //steps[i][j-1] + 1, delete last char in s2, plus steps s1[0...i-1] -> s2[0...j-2]
                    //steps[i-1][j-1] + 1, replace last char in s1, no matter what last char s2 has
                    steps[i][j] = 1 + Math.min(Math.min(steps[i-1][j], steps[i][j-1]), steps[i-1][j-1]);
			}
		}

		return steps[m][n] == 1;
	}

	/*	Solution 2
		
		Brute force, check every possible result of the original string

		Time: O(3^n)
	*/
	static private boolean oneWay2(String s1, String s2) {
		if (s1 == null || s2 == null)
			return s1 == s2;
		if (Math.abs(s1.length() - s2.length()) > 1) //distance greater than 1
			return false;

		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();

		//insert a char to the one with less chars, or replace chars when has same
		//number of chars
		if (c1.length < c2.length)
			return oneWay2_insert(c1, c2);
		else if (c1.length > c2.length)
			return oneWay2_insert(c2, c1);
		else //c1.length == c2.length
			return oneWay2_replace(c1, c2);
	}

	static private boolean oneWay2_insert(char[] c1, char[] c2) {
		return true;
	}

	static private boolean oneWay2_replace(char[] c1, char[] c2) {
		return true;
	}

	/*	Solution 3

	*/
}