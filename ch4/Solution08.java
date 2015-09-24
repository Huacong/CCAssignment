/* 
4.8 First Common Ancestor:
	Design an algorithm and write code to find the first common ancestor of two 
	nodes in a binary tree. Avoid storing additional nodes in a data structure. 
	NOTE: This is not necessarily a binary search tree.
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
	public TreeNode root;
	public TreeNode node1;
	public TreeNode node2;

	public Input(TreeNode root, TreeNode node1, TreeNode node2) {
		this.root = root;
		this.node1 = node1;
		this.node2 = node2;
	}
}

class Result {
	public TreeNode ancestor; //the common ancestor
}

public class Solution08 {
	public static void main(String[] args) {
		System.out.println("4.8 First Common Ancestor");

		test();
	}

	private static void test() {
		Input input = generateTree();

		TreeNode result = firstCommonAncestor(input.root, input.node1, input.node2);
		
		if (result == null)
			System.out.println("null");
		else
			System.out.println(result.val);
	}

	private static Input generateTree() {
		TreeNode n0 = new TreeNode(0);
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5);

		n0.left = n1;
		n0.right = n2;

		n1.left = n3;
		n1.right = n4;

		n3.left = n5;

		return new Input(n0, n4, n5);
	}

	/*	Solution
	
	Analysis:
		The first common ancestor must have one of the nodes in its left subtree
		and the other one in the right subtree.

		This also applies to multi-children tree

	Assumption:
		TreeNode is the general one without pointer to its parent. The input is
		the root of the tree and reference to the two nodes that need to find 
		the first common ancestor

	Implementation: 
		Recursively check if every subtree contains one target node
	
		Time: O(n) for traversal
		Space: O(1)
	*/
	private static TreeNode firstCommonAncestor(TreeNode root, TreeNode node1, TreeNode node2) {
		Result result = new Result();

		containNodes(root, node1, node2, result);

		return result.ancestor; //could be null
	}

	public static boolean containNodes(TreeNode root, TreeNode node1, TreeNode node2, Result result) {
		if (root == null)
			return false;
		
		if (root == node1 || root == node2) //if find the target nodes on the half way
			return true;

		boolean leftContains = containNodes(root.left, node1, node2, result);
		boolean rightContains = containNodes(root.right, node1, node2, result);

		//already find out the first common ancestor
		if (result.ancestor != null)
			return true;

		//check if this is the first common ancestor
		if (leftContains && rightContains) {
			result.ancestor = root;
			return true;
		} else if (leftContains || rightContains) {
			//only have one node in the tree
			return true;
		}

		return false;
	}
}