/* 
10.4 Sorted Search, No Size
	 You are given an array-like data structure Listy which lacks a size method. 
	 It does, however, have an elementAt(i) method that returns the element at 
	 index i in O(1) time. If i is beyond the bounds of the data structure, it 
	 it returns -1. (For this reason, the data structure only supports positive 
	 integers.) Given a Listy which contains sorted, positive integers, find the 
	 index at which an element x occurs. If x occurs multiple time, you may 
	 return any index.
*/

import java.lang.*;
import java.io.*;
import java.util.*;

class Listy {
	private int[] ary;

	public Listy (int[] ary) {
		this.ary = ary;
	}

	public int elementAt(int index) {
		if (ary == null || index >= this.ary.length || index < 0)
			return -1;
		else
			return this.ary[index];
	}
}

class Input {
	Listy listy;
	int target;

	public Input(Listy listy, int target) {
		this.listy = listy;
		this.target = target;
	}
}

public class Solution04 {
	public static void main(String[] args) {
		System.out.println("10.4 Sorted Search, No Size");

		test();
	}

	private static void test() {
		Input input = generateInput();

		int pos = sortedSearch(input.listy, input.target);

		System.out.println(pos);
	}

	private static Input generateInput() {
		int[] nums = {1, 3, 5, 7, 9, 11};
		int target = 9; //pos = 4

		return new Input(new Listy(nums), target);
	}

	/* Solutions */

	/* Solution
	Assumptions:
		The element in the Listy is integer
	
	Implementation:
		Start from i = 0 and increase the search range twice every time to find 
		out the range of target, i.e. last_i to i. Then do binary search in 
		the given range

		Time: O(logn)
		Space: O(1)
	*/
	private static int sortedSearch(Listy listy, int target) {
		if (listy == null || listy.elementAt(0) == -1)
			return -1;

		int lo = 0;
		int hi = 0;
		//find out the range of target, increase the range by 2 every time
		while (listy.elementAt(hi) != -1 && listy.elementAt(hi) < target) {
			lo = hi;
			hi = hi * 2 + 1;
		}

		//binary search
		while(lo <= hi) {
			int mi = lo + (hi - lo) / 2;
			
			if (listy.elementAt(mi) == target)
				return mi;
			else if (listy.elementAt(mi) > target)
				hi = mi - 1;
			else //[mi] < target 
				lo = mi + 1;
		}

		return -1;
	}
}