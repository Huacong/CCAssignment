/* 
10.5 Sparse Search
	 Given a sorted array of strings that is interspersed with empty strings, 
	 write a method to find the location of a given string.

	 EXAMPLE
	 Input: ball, {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "",
	 		""}
	 Output: 4
*/

import java.lang.*;
import java.io.*;
import java.util.*;

class Input {
	String[] strs;
	String target;

	public Input(String[] strs, String target) {
		this.strs = strs;
		this.target = target;
	}
}

public class Solution05 {
	public static void main(String[] args) {
		System.out.println("10.5 Spare Search");

		test();
	}

	private static void test() {
		Input input = generateInput();

		int pos = sparseSearch(input.strs, input.target);

		System.out.println(pos);
	}

	private static Input generateInput() {
		String[] strs = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
		String target = "ball"; //pos = 4

		return new Input(strs, target);
	}

	/* Solutions */

	/*Solution
		Use binary search to search the target, if mid is empty, set the nearest
		non-empty string as mid

		Time: O(m*n), m for compare string, n is the worst case, binary search
			  become liner search
		Space: O(1)
	*/
	private static int sparseSearch(String[] strs, String target) {
		if (strs == null || strs.length == 0)
			return -1;

		int lo = 0;
		int hi = strs.length - 1;
		while (lo <= hi) {
			int mi = lo + (hi - lo) / 2;

			int compare = target.compareTo(strs[mi]);
			if (compare == 0) //include empty str
				return mi;

			if (strs[mi].isEmpty()) { //find the nearest non-empty str
				int l = mi - 1;
				int r = mi + 1;

				while (true) {
					if (l < lo && r > hi)
						return -1;

					if (l >= lo) {
						if (!strs[l].isEmpty()) {
							mi = l;
							break;
						} else {
							l--;
						}
					} 

					if (r <= hi) {
						if (!strs[r].isEmpty()) {
							mi = r;
							break;
						} else {
							r++;
						}
					}
				}
			}

			//compare again for the possible new mi
			compare = target.compareTo(strs[mi]);
			if (compare == 0)
				return mi;
			else if (compare > 0) {//target > mi
				lo = mi + 1;
			} else {//target < mi,
				hi = mi - 1;
			}
		}

		return -1;
	}
}