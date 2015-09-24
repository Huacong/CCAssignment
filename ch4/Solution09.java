/* 
4.9 BST Sequences:
	A binary search tree was created by traversing through an array from left 
	to right and inserting each element. Given a binary search tree with distinct
	elements, print all possible arrays that could have led to this tree.

	EXAMPLE
	Input:
				2
		  	  /   \
			1		3
	Output: {2, 1, 3}, {2, 3, 1}
*/

import java.io.*;
import java.util.*;

class TreeNode {
	public int val;
	
	public TreeNode left;
	public TreeNode right;

	TreeNode (int val) { this.val = val; }
}

public class Solution09 {
	public static void main(String[] args) {
		System.out.println("4.9 BST Sequences");

		test();
	}

	private static void test() {
		TreeNode root = generateTree();
		List<List<Integer>> results = BSTSequences(root);

		for (List<Integer> result : results) {
			for (Integer i : result) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	private static TreeNode generateTree() {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);

		n2.left = n1;
		n2.right = n3;

		return n2;
	}

	/*	Solution:

	Analysis:
		Since all the nodes smaller than root are inserted to the left subtree, the 
		greater ones are inserted to the right. Thus the order of the left nodes 
		won't affect the right nodes.

		However, the nodes in the same tree will affect each other since the one comes
		first will become the root.

		So giving the possible orders of left tree, they can interleave with the 
		possible orders of right tree, as long as the relative order of the nodes 
		in the same side is keeped.
	*/

	private static List<List<Integer>> BSTSequences(TreeNode root) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();

		//both subtrees are empty, base case
		if (root.left == null && root.right == null) {
			List<Integer> tmp = new ArrayList<Integer>();
			tmp.add(root.val);

			results.add(tmp);

			return results;
		}

		if (root.left == null)
			return BSTSequences(root.right);

		if (root.right == null)
			return BSTSequences(root.left);

		//both subtrees are not empty
		List<List<Integer>> leftResults = BSTSequences(root.left);
		List<List<Integer>> rightResults = BSTSequences(root.right);

		//both of them are not empty, interleaving all the pairs of left and right lists
		for (List<Integer> list1 : leftResults) {
			for (List<Integer> list2 : rightResults) {
				List<List<Integer>> tmp = getInterleaving(list1, list2);

				for (List<Integer> list : tmp) {
					list.add(0, root.val);//add the root to the head
					results.add(list);
				}
			}
		}

		return results;
	}

	//insert list2 to list1
	private static List<List<Integer>> getInterleaving(List<Integer> list1, List<Integer> list2) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();

		if (list1.size() == 0 && list2.size() == 0) 
			return results;

		if (list1.size() == 0) {
			results.add(new ArrayList<Integer>(list2));
			return results;
		} 

		if (list2.size() == 0) {
			results.add(new ArrayList<Integer>(list1));
			return results;
		}
 
		int node1 = list1.get(0);
		list1.remove(0);
		List<List<Integer>> results1 = getInterleaving(list1, list2);
		list1.add(0, node1);

		for (List<Integer> result : results1) {
			result.add(0, node1);
			results.add(result);
		}

		int node2 = list2.get(0);
		list2.remove(0);
		List<List<Integer>> results2 = getInterleaving(list1, list2);
		list2.add(0, node2);

		for (List<Integer> result : results2) {
			result.add(0, node2);
			results.add(result);
		}

		return results;
	}
}