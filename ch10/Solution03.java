/* 
10.3 Search in Rotated Array
	 Given a sorted array of n integers that has been rotated an unknown number 
	 of times, write code to find an element in the array. You may assume that
	 the array was originally sorted in increasing order.

	 EXAMPLE
	 Input: find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
	 Output: 8 (the index of 5 in the array)
*/

import java.lang.*;
import java.io.*;
import java.util.*;

class Input {
	int[] nums;
	int target;

	public Input(int[] nums, int target) {
		this.nums = nums;
		this.target = target;
	}
}

public class Solution03 {
	public static void main(String[] args) {
		System.out.println("10.3 Search in Rotated Array");

		test();
	}

	private static void test() {
		Input input = generateInput();

		int pos = searchInRotatedArray(input.nums, input.target);

		System.out.println(pos);
	}

	private static Input generateInput() {
		int[] nums = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
		int target = 5; //pos = 8

		return new Input(nums, target);
	}

	/* Solutions 
	Assumptions:
		There are duplicates in the array, return any index of the target if 
		occur multiple times.

		Can be used for searching in non-duplicates array

		Time: O(n) in worst case
		Space: O(1)
	*/
	private static int searchInRotatedArray(int[] nums, int target) {
		if (nums == null || nums.length == 0)
            return -1;
            
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low)/2; //avoid overflow, round down
            if (nums[mid] == target) {
                return mid;
               
            } else if (target < nums[mid]) {
                if (nums[mid] == nums[low]) //can't decide on which side, try ++
                    low++;
                else if (nums[low] > target && nums[mid] > nums[low])
                	//left side in order and nums[low] > target, on the right side
                    low = mid + 1;
                else
                    high = mid - 1;
                    
            } else { //target > nums[mid]
                if (nums[mid] == nums[high]) //can't decide on which side, try --
                    high--;
                else if (nums[high] < target && nums[mid] < nums[high])
                	//right side in order and nums[high] < target, on the left side
                    high = mid - 1;
                else
                    low = mid + 1;
            }
        }
        
        return -1;
	}
}