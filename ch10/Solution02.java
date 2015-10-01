/* 
10.2 Group Anagrams
	 Write a method to sort an array of strings so that all the anagrams are 
	 next to each other.
*/

import java.lang.*;
import java.io.*;
import java.util.*;

public class Solution02 {
	public static void main(String[] args) {
		System.out.println("10.2 Group Anagrams");

		test();
	}

	private static void test() {
		String[] strs = generateInput();

		//String[] result = groupAnagrams1(strs);
		String[] result = groupAnagrams2(strs);

		System.out.println(Arrays.toString(result));
	}

	private static String[] generateInput() {
		//group1: abc bac
		//group2: def fed
		//group3: 123 321
		String[] strs = {"abc", "def", "123", "bac", "fed", "321"};

		return strs;
	}

	/* helpers */
	//klogk for sorting, k for gnerate new string
	private static String toSortedString(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return new String(chars);
	}

	/* Solutions */

	/* Solution1
	Implementation:
		Use comparator to sort the strings, anagrams will be consider as equal, 
		other strings will be convert to basic pattern (chars inside the string
		are in order) and sorted in alphebetical order

		Time: O(nlogn * klogk), where k is the longest length of string, n 
			  is the number of all strings. nlogn comparasions and klogk 
			  every time
		Space: O(k), every compare will generate temp string
	*/
	private static String[] groupAnagrams1(String[] strs) {
		class AnagramComparator implements Comparator<String> {
			public int compare(String s1, String s2) {
				String a = Solution02.toSortedString(s1);
				String b = Solution02.toSortedString(s2);

				return a.compareTo(b);
			}
		}

		Arrays.sort(strs, new AnagramComparator());

		return strs;
	}

	/* Solution2
	Implementation:
		Use hashmap to group anagrams, finally put them into a new array. Group
		of anagrams will be sorted randomly.

		If group of anagrams need to be sorted in alphabetical order according 
		to their basic pattern, use treemap instead and get(), put() will be 
		O(logn) rather than O(1)

		Time: O(nlogn * klogk), where k is the longest length of string, n 
			  is the number of all strings. klogk to sort every str and
			  O(1) to put them to hashmap, totally n strs
		Space: O(n + k), n for hashmap and result array (reference), k for 
			   temp string when sorting string
	*/
	private static String[] groupAnagrams2(String[] strs) {
		//the value just record list of the index for strings
		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();

		//group strings
		for (int i = 0; i < strs.length; i++) {
			String key = toSortedString(strs[i]);//O(klogk)

			List<Integer> list = map.get(key);
			if (list == null) {
				list = new ArrayList<Integer>();
				list.add(i);
				map.put(key, list);
			} else {
				list.add(i);
			}
		}

		//generate result
		int i = 0;
		String[] result = new String[strs.length];
		for (List<Integer> list : map.values()) {
			 for (Integer index : list) {
			 	result[i] = strs[index];
			 	i++;
			 }
		}

		return result;
	}
}