/* 
1.2 Check Permutation: 
	Given two strings, write a method to decide if one is a permutation of the 
	other.
*/

/*
Edge case: null, "", s1.length() != s2.length()
*/

import java.io.*;
import java.util.*;

public class Solution02 {
	static public void main(String[] args) throws IOException {
		List<String> list = input();

		//algorithm
		boolean b = checkPermutation1(list.get(0), list.get(1));
		//boolean b = checkPermutation2(list.get(0), list.get(1));
		//boolean b = checkPermutation3(list.get(0), list.get(1));

		output(b);
	}

	/* Helpers */
	//no exception handling
	static private List<String> input() throws IOException {
		System.out.println("1.2 Check Permutation");
		System.out.print("Please input strings (separate by space. E.g., abc bac): ");

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

		Assumption: Only have basic ASCII chars (0-127), the appear times of
		one chars can't exceed the range of int.

	   	Use bit vector to count the chars of 1st string, check if the 2nd
	   	string has the same chars.

	   	Time: O(n)
	   	Space: O(1)
	*/
	static private boolean checkPermutation1(String s1, String s2) {
		if (s1 == null || s2 == null)
			return s1 == null && s2 == null;
		if (s1.length() == 0 || s2.length() == 0)
			return s1.length() == s2.length();
		if (s1.length() != s2.length())
			return false;

		int[] times = new int[128]; //default values are 0

		// count s1
		for (int i = 0; i < s1.length(); i++)
			times[s1.charAt(i)]++;

		// check s2
		for (int i = 0; i < s2.length(); i++) {
			times[s2.charAt(i)]--;

			if (times[s2.charAt(i)] < 0)
				return false;
		}

		return true;
	}

	/*	Solution 2
		
		Use hash table to count the chars of 1st string, check if the 2nd
	   	string has the same chars. Not specified to ASCII character set

		Time: O(n), if get and put of hash set are O(1) (get and put could 
			  be O(logn))
		Space: O(1), related to the size of the character set. Char in java
			   is 16-bit, thus no more than 65535 chars
	*/
   static private boolean checkPermutation2(String s1, String s2) {
		if (s1 == null || s2 == null)
			return s1 == null && s2 == null;
		if (s1.length() == 0 || s2.length() == 0)
			return s1.length() == s2.length();
		if (s1.length() != s2.length())
			return false;

		Map<Character, Integer> map = new HashMap<Character, Integer>();
		Integer times;

		// count s1
		for (int i = 0; i < s1.length(); i++) {
			times = map.get(s1.charAt(i));
			if (times != null)
				map.put(s1.charAt(i), times + 1);
			else
				map.put(s1.charAt(i), 1);
		}

		// check s2
		for (int i = 0; i < s2.length(); i++) {
			times = map.get(s2.charAt(i));

			if (times != null) {
				if (times > 0)
					map.put(s1.charAt(i), times - 1);
				else // appear times of char c of s2 is more than s1
					return false;
			} else // new char c that didn't appear in s1
				return false;
		}

		return true;
	}

	/*	Solution 3
		
		Sort the strings and check if they are the same

		Time: O(nlogn), O(nlogn) for sort, O(n) for check
		Space: O(1), not consider the char array created by s.toCharArray()
	*/
	static private boolean checkPermutation3(String s1, String s2) {
		if (s1 == null || s2 == null)
			return s1 == null && s2 == null;
		if (s1.length() == 0 || s2.length() == 0)
			return s1.length() == s2.length();
		if (s1.length() != s2.length())
			return false;

		char[] chars1 = s1.toCharArray();
		Arrays.sort(chars1);

		char[] chars2 = s2.toCharArray();
		Arrays.sort(chars2);

		return Arrays.equals(chars1, chars2);
	}
}