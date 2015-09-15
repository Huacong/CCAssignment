/*
2.7 Intersection:
	Given two (singly) linked lists, determine if two lists intersect. Return
	the intersecting node. Note that the intersection is defined based on 
	reference, not value. That is, if the kth node of the linked list is the
	exact same node (by reference) as the jth node of the second linked list,
	then they are intersecting. 
*/

/*
Edge case: null, no intersection
*/

import java.io.*;
import java.util.*;

//Definition of list node
class ListNode {
	int value;
	ListNode next;

	ListNode(int x) {
		this.value = x;
	}
}

class InputArgs {
	ListNode l1;
	ListNode l2;

	InputArgs(ListNode l1, ListNode l2) {
		this.l1 = l1;
		this.l2 = l2;
	}
}

public class Solution07 {
	static public void main(String[] args) throws IOException {
		InputArgs inputArgs = input();

		ListNode result = intersection(inputArgs.l1, inputArgs.l2);

		output(result);
	}

	/* Helpers */
	static private InputArgs input() throws IOException {
		System.out.println("2.7 Intersection");

		//read number of nodes for list 1
		System.out.print("Please input the number of nodes in the linked list 1: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		//read linked list
		System.out.println();
		System.out.print("Please input value of nodes for list 1 (seperate by space, e.g., 1 2 3): ");
		
		ListNode dummy1 = new ListNode(0);
		ListNode pre = dummy1;
		Map<Integer, ListNode> map = new HashMap<Integer, ListNode>();
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			ListNode cur = new ListNode(x);

			map.put(x, cur); //only the last node with same value will be stored

			pre.next = cur;
			pre = cur;
		}

		//read number of nodes for list 2
		System.out.println();
		System.out.print("Please input the number of nodes in the linked list 2 (if " + 
			"intersect, only input the number of nodes at and before intersection): ");
		n = sc.nextInt();

		//read linked list
		System.out.println();
		System.out.print("Please input value of nodes for list 2 (if intersect, only " + 
			"input the value at and before intersection): ");
		
		ListNode dummy2 = new ListNode(0);
		pre = dummy2;
		for (int i = 0; i < n; i++) {
			ListNode cur = new ListNode(sc.nextInt());
			pre.next = cur;
			pre = cur;
		}

		//intersect lists
		pre.next = map.get(pre.value);

		return new InputArgs(dummy1.next, dummy2.next);
	}

	static private void output(ListNode result) {
		System.out.println();
		System.out.println("The intersection node is: ");
		if (result != null)
			System.out.print(result.value);
		System.out.println();
	}

	/* Solutions */

	/*
		Assumption: both of the lists don't have loop
	*/

	/*	Solution 1
		
		Check if the end of the lists are the same. If so, compute the difference between
		their length, and the longer one skip the difference, then walk together. The 
		collision point will be the intersection point.

		Time: O(n)
		Space: O(1)
	*/
	static private ListNode intersection(ListNode l1, ListNode l2) {
		//get the tail and length of l1
		ListNode cur1 = l1;
		ListNode tail1 = l1;
		int len1 = 0;
		for (; cur1 != null; cur1 = cur1.next) {
			len1++;
			tail1 = cur1;
		}

		//get the tail and length of l2
		ListNode cur2 = l2;
		ListNode tail2 = l2;
		int len2 = 0;
		for (; cur2 != null; cur2 = cur2.next) {
			len2++;
			tail2 = cur2;
		}

		if (tail1 != tail2)
			return null;

		//get difference
		int diff = Math.abs(len1 - len2);

		//set list pointer
		ListNode shorter = (len1 < len2) ? l1 : l2;
		ListNode longer = (len1 >= len2) ? l1 : l2;

		//the longer list skip some nodes
		for (int count = 0; count < diff; count++)
			longer = longer.next;

		//both lists walk together and the collision point is the intersection point
		while (shorter != longer) {
			shorter = shorter.next;
			longer = longer.next;
		}

		return shorter;
	}
}