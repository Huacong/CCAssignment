/* 
4.3 List of Depths:
	Given a binary tree, design an algorithm which creates a linked list of all
	the nodes at each depth (e.g., if you have a tree with depth D, you'll have
	D linked lists).
*/

import java.io.*;
import java.util.*;

class TreeNode {
	public int val;
	
	public TreeNode left;
	public TreeNode right;

	TreeNode (int val) { this.val = val; }
}

public class Solution03 {
	public static void main(String[] args) {
		System.out.println("4.3 List of Depths");

		test();
	}

	private static void test() {
		TreeNode root = generateTree();

		List<List<TreeNode>> result = listOfDepths(root);

		for (List<TreeNode> levelNodes : result) {
			for (TreeNode node : levelNodes) {
				System.out.print(node.val + " ");
			}

			System.out.println();
		}
	}

	private static TreeNode generateTree() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);

		n0.left = n1;
		n0.right = n2;
		n1.left = n3;
		n1.right = n4;

		return n0;
	}

	/*	Solution

		Use BFS to traverse the tree in level ordering.

		Time: O(n) for tranversal
		Space: O(1)
	*/
	static private List<List<TreeNode>> listOfDepths(TreeNode root) {
		List<List<TreeNode>> result =  new ArrayList<List<TreeNode>>();
		if (root == null)
			return result;

		ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			int size = queue.size();//num of nodes in the current level

			List<TreeNode> levelNodes = new ArrayList<TreeNode>();

			for (int i = 0; i < size; i++) {
				//make linked list
				TreeNode node = queue.poll();
				levelNodes.add(node);

				//queue next level
				if (node.left != null)
					queue.offer(node.left);

				if (node.right != null)
					queue.offer(node.right);
			}

			result.add(levelNodes);
		}

		return result;
	}
}