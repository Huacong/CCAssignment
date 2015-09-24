 /* 
4.11 Random Node:
	You are implementing a binary tree class from scratch which, in addition to
	insert, find, and delete, has a method getRandomNode() which returns a random
	node from the tree. All nodes should be equally likely to be chosen. Design
	and implement an algorithm for getRandomNode, and explain how you would implement
	the rest of the methods.
*/

import java.io.*;
import java.util.*;

class TreeNode {
	public int val;

	public TreeNode left;
	public TreeNode right;

	int size; //size of the tree that use this node as root

	TreeNode (int val) { 
		this.val = val;
		this.size = 1;
	}
}

/*	Solution

Assumption:
	If the there are duplicates, the element insert later will be on the left 
	of node with same value. Find() will return the upper node of same value.

*/

class BST {
	public TreeNode root;
	
	public TreeNode getRandomNode() {
		//random number from 1 to n, index of the node
		int ith = new Random().nextInt(this.root.size) + 1;

 		System.out.println("The pos of the random node: " + ith);

		return getithNode(root, ith);
	}

	private TreeNode getithNode(TreeNode root, int ith) {
		int leftSize = root.left == null ? 0 : root.left.size;

		if (leftSize == ith - 1)
			return root;
		else if (leftSize > ith) //on the left subtree
			return getithNode(root.left, ith);
		else //on the right subtree
			//substract nodes on the left tree and current node
			return getithNode(root.right, (ith - leftSize - 1));
	}

	public void insert(TreeNode node) {
		if (this.root == null) 
			this.root = node;
		else
			insertHelper(this.root, node);
	}

	public void insertHelper(TreeNode root, TreeNode node) {
		//find the leaf position to insert
		if (node.val <= root.val) { //<=
			if (root.left == null)
				root.left = node;
			else
				insertHelper(root.left, node);

		} else { // >
			if (root.right == null)
				root.right = node;
			else
				insertHelper(root.right, node);
		}

		root.size++;
	}

	public TreeNode find(int target) {
		TreeNode node = root;

		while (node != null) {
			if (node.val == target)
				return node;
			else if (node.val >= target)
				node = node.left;
			else
				node = node.right;
		}

		return null;
	}


}

public class Solution11 {
	public static void main(String[] args) {
		System.out.println("4.11 Random Node");

		test();
	}

	private static void test() {
		BST bst = generateTree();

		printTree(bst.root);

		System.out.println("Find target 3: " + bst.find(3).val);

		System.out.println("Get ramdon node: " + bst.getRandomNode().val);
	}

	private static BST generateTree() {
		BST bst = new BST();

		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);

		bst.insert(n1);
		bst.insert(n0);
		bst.insert(n2);
		bst.insert(n3);
		bst.insert(n4);

		return bst;
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