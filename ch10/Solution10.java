/* 
10.10 Rank from Stream
	  Imagine you are reading in a stream of integers. Periodically, you wish 
	  to be able to lok up the rank of a number x (the number of values less 
	  than or equal to x). Implement the data structures and algorithms to 
	  support these operations. That is, implement the method track(int x) which 
	  is called when each number is generated, and the method getRankOfNumber(
	  int x), which returns the number of values less than or equal to x (not 
	  including x itself).

	  EXAMPLE
	  Stream (in order of apperance): 5, 1, 4, 4, 5, 9, 7, 13, 3
	  Sort: 1, 3, 4, 4, 5, 5, 7, 9, 13
	  getRankOfNumber(1) = 0
	  getRankOfNumber(3) = 1
	  getRankOfNumber(4) = 3
*/

import java.lang.*;
import java.io.*;
import java.util.*;

/* Solution
Assumptions:
   If there are duplicates, getRankOfNumber() will return the last index of the
   number

Implementation:
   Use binary search tree to maintain the elements, use an additional field 
   "lsize" to record the nodes on its left subtree, which can be used to compute
   the rank of the node.

   track() is actually insert()
   getRankOfNumber() is actually get()

   Time: O(n) since the tree may not be balanced, but average O(logn) for 
   		 insert() and get()
   Space: O(n) for the tree
*/


class TreeNode {
	public int val;

	public TreeNode left;
	public TreeNode right;

	int lsize;

	TreeNode (int val) { 
		this.val = val;
		this.lsize = 0;
	}
}

class TrackTree {
	TreeNode root;

	public void track(int val) {//insert
		if (this.root == null) 
			this.root = new TreeNode(val);
		else
			insertHelper(this.root, val);
	}

	private void insertHelper(TreeNode root, int val) {
		if (val <= root.val) { //insert to left subtree
			if (root.left == null)
				root.left = new TreeNode(val);
			else
				insertHelper(root.left, val);

			root.lsize++;//new node inserts into root.left

		} else { //insert to right subtree
			if (root.right == null)
				root.right = new TreeNode(val);
			else
				insertHelper(root.right, val);
		}
	}

	public int getRankOfNumber(int val) {
		return getRankHelper(this.root, val);
	}

	public int getRankHelper(TreeNode root, int val) {
		if (root == null)
			return -1;

		if (val == root.val)
			return root.lsize; //rank in current subtree
		else if (val <= root.val) //go to left subtree
			return getRankHelper(root.left, val);
		else //go to right subtree
			return root.lsize + 1 + getRankHelper(root.right, val);
	}

}

class Input {
	int[] nums;
	int target;

	public Input(int[] nums, int target) {
		this.nums = nums;
		this.target = target;
	}
}

public class Solution10 {
	public static void main(String[] args) {
		System.out.println("10.10 Rank from Stream");

		test();
	}

	private static void test() {
		Input input = generateInput();

		TrackTree t = new TrackTree();

		for (int i = 0; i < input.nums.length; i++)
			t.track(input.nums[i]);

		for (int i = 0; i < input.nums.length; i++)
			System.out.format("Rank of %d: %d\n", input.nums[i], t.getRankOfNumber(input.nums[i]));
	}

	private static Input generateInput() {
		int[] nums = {5, 1, 4, 4, 5, 9, 7, 13, 3};
		int target = 5;

		return new Input(nums, target);
	}
}