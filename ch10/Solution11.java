/* 
10.11 Peaks and Valleys
	  In an array of integers, a "peak" is an element which is greater than or 
	  equal to the adjacent integers and a "valley" is an element which is less
	  than or equal to the adjacent integers. For example, in the array {5, 8, 
	  6, 2, 3, 4, 6}, {8, 6} are peaks and {5, 2} are valleys. Given an array 
	  of integers, sort the array into an alternating sequence of peaks and 
	  valleys.

	  EXAMPLE
	  Input: {5, 3, 1, 2, 3}
	  Output: {5, 1, 3, 2, 3}
*/

/*
Edge case: 
*/

import java.io.*;
import java.util.*;

public class Solution11 {
	public static void main(String[] args) {
		System.out.println("10.11 Peaks and Valleys");

		test();
	}

	private static void test() {
		int[] nums = generateInput();

		System.out.println("Before sorting:");
		System.out.println(Arrays.toString(nums));

		peaksAndValleys(nums);

		System.out.println("After sorting:");
		System.out.println(Arrays.toString(nums));
	}

	private static int[] generateInput() {
		int[] nums = {5, 3, 1, 2, 3};

		return nums;
	}

	/* Solutions 
	Assumptions:
		No duplicates in the array, otherwise we need to sort and interleave the 
		bigger elements with smaller elements
	
	Implementation:
		Swap element to make sure that every small group has a peak in the 
		center (center at ..., i, i+2, ...)

		Time: O(n)
		Space: O(1)
	*/
	private static void peaksAndValleys(int[] nums) {
		if (nums == null || nums.length <= 2)
			return;

		for (int i = 1 ; i+1 < nums.length; i += 2) {
			if (nums[i-1] > nums[i])
				swap(nums, i-1, i);

			if (nums[i+1] > nums[i])
				swap(nums, i, i+1);
		}
	}

	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}