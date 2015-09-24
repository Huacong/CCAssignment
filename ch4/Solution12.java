 /* 
4.12 Paths with Sum:
	You are given a binary tree in which each node contains an integer value
	(which might be positive or negative). Design an algorithm to count the
	number of paths that sum to a given value. The path does not need to start 
	or end at the root or a leaf, but it must go downwards (traveling only from
	parent nodes to child nodes)
*/

import java.io.*;
import java.util.*;

class TreeNode {
	public int val;

	public TreeNode left;
	public TreeNode right;

	TreeNode (int val) { this.val = val; }
}

class Input {
	TreeNode root;
	int sum;

	public Input(TreeNode root, int sum) {
		this.root = root;
		this.sum = sum;
	}
}

public class Solution12 {
	public static void main(String[] args) {
		System.out.println("4.12 Paths with Sum");

		test();
	}

	private static void test() {
		Input input = generateTree();

		System.out.println(pathsWithSum(input.root, input.sum));
	}

	/*
		{1, 2}
		{2, 1}
		{1, 2, 0}
		{1, 3, -1}
		{3}
	*/
	private static Input generateTree() {
		TreeNode n0 = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(3);
		TreeNode n3 = new TreeNode(1);
		TreeNode n4 = new TreeNode(0);
		TreeNode n5 = new TreeNode(-1);
		TreeNode n6 = new TreeNode(4);

		n0.left = n1;
		n0.right = n2;

		n1.left = n3;
		n1.right = n4;

		n2.left = n5;
		n2.right = n6;

		return new Input(n0, 3);
	}

	/*	Solution
		
		Traverse all path, use hash map to store the temperary sum of the path
		before
	*/

	//key is the path sum, value is the count of the sum in the current path
	private static Map<Integer, Integer> sumCount = new HashMap<Integer, Integer>();

	private static int pathsWithSum(TreeNode root, int target) {
		if (root == null)
			return 0;

		incrementSumCount(0, 1);

		return countPathsWithSum(root, target, 0);
	}

	private static int countPathsWithSum(TreeNode root, int target, int accumulator) {
		if (root == null)
			return 0;

		accumulator += root.val;
		incrementSumCount(accumulator, 1);

		//count paths ending at the current node
		int sum = accumulator - target;
		int totalPaths = sumCount.containsKey(sum) ? sumCount.get(sum) : 0;

		//count paths on the left and right subtree
		totalPaths += countPathsWithSum(root.left, target, accumulator);
		totalPaths += countPathsWithSum(root.right, target, accumulator);

		incrementSumCount(accumulator, -1);

		return totalPaths;
	}

	private static void incrementSumCount(int sum, int delta) {
		Integer count = sumCount.get(sum);

		if (count == null)
			sumCount.put(sum, delta);
		else
			sumCount.put(sum, count + delta);
	}
}