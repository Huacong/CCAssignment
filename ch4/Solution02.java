/* 
4.2 Minimal Tree:
	Given a sorted (increasing order) array with unique integer elements, write
	an algorithm to create a binary search tree with minimal height.
*/

import java.io.*;
import java.util.*;

class TreeNode {
	public int val;
	
	public TreeNode left;
	public TreeNode right;

	TreeNode (int val) { this.val = val; }
}

public class Solution02 {
	public static void main(String[] args) {
		System.out.println("4.2 Minimal Tree");

		test1(); //odd
		test2(); //even
	}

	private static void test1() {
		int[] array = {1, 2, 3, 4, 5};
		TreeNode head = minimalTree(array, 0, array.length - 1);
		printTree(head);
	}

	private static void test2() {
		int[] array = {1, 2, 3, 4, 5, 6};
		TreeNode head = minimalTree(array, 0, array.length - 1);
		printTree(head);
	}

	/*	Solution 

		Equally divide the array into two subarray, let the middle number 
		become the root, left subarray as left tree, right subarray as right
		tree.

		Since the nodes in left tree and right tree are balanced, the height
		will be minimal.

		Time: O(n) for traversal
		Space: O(1)
	*/
	private static TreeNode minimalTree(int[] array, int start, int end) {
		if (start > end) {
			return null;
		}

		int mid = start + (end - start + 1)/2; //round up

		TreeNode root = new TreeNode(array[mid]);

		root.left = minimalTree(array, start, mid - 1);
		root.right = minimalTree(array, mid + 1, end);

		return root;
	}

	//BFS
	private static void printTree(TreeNode head) {
		ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.offer(head);

		while(!queue.isEmpty()) {
			int size = queue.size();

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();

				System.out.print(node.val + " ");

				if (node.left != null)
					queue.offer(node.left);

				if (node.right != null)
					queue.offer(node.right);
			}

			System.out.println();
		}
	}
}