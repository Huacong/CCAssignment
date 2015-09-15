/*
2.3 Delete Middle Node:
	Implement an algorithm to delete a node in the middle of a singly linked 
	list, given only access to that node.

	EXAMPLE:
	Input:	the node c from the linked list a->b->c->d->e
	Result: nothing is returned, but the new linked list looks like a->b->d->e
*/


/*
Edge case: null, node.next == null
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
	ListNode head;
	ListNode toBeDel;

	InputArgs(ListNode head, ListNode toBeDel) {
		this.head = head;
		this.toBeDel = toBeDel;
	}
}

public class Solution03 {
	static public void main(String[] args) throws IOException {
		InputArgs inputArgs = input();

		boolean b = deleteMiddleNode(inputArgs.toBeDel);

		output(b, inputArgs.head);
	}

	/* Helpers */
	static private InputArgs input() throws IOException {
		System.out.println("2.3 Delete Middle Node");

		//read node to be deleted
		System.out.print("Please input the value of nodes to be deleted (should " + 
			"be in the list, the last node with the same value will be deleted): ");
		Scanner sc = new Scanner(System.in);
		int x = sc.nextInt();

		//read number of nodes
		System.out.println();
		System.out.print("Please input the number of nodes in the linked list: ");
		int n = sc.nextInt();

		//read linked list
		System.out.println();
		System.out.print("Please input value of nodes (seperate by space, e.g., 1 2 3): ");
		
		ListNode dummy = new ListNode(0);
		ListNode pre = dummy;
		ListNode toBeDel = null;
		for (int i = 0; i < n; i++) {
			ListNode cur = new ListNode(sc.nextInt());

			if (cur.value == x)
				toBeDel = cur;

			pre.next = cur;
			pre = cur;
		}

		return new InputArgs(dummy.next, toBeDel);
	}

	static private void output(boolean b, ListNode head) {
		System.out.println();
		System.out.println("After deleting:");
		System.out.println(b);

		for (; head != null; head = head.next)
			System.out.print(head.value + " ");

		System.out.println();
	}

	/* Solutions */
	/*	Solution 1
		
		Simply copy the value of next node to current node, and delete next node

		Time: O(n)
		Space: O(1)
	*/
	static private boolean deleteMiddleNode(ListNode toBeDel) {
		if (toBeDel == null || toBeDel.next == null) //next node can't be null
			return false;

		toBeDel.value = toBeDel.next.value;
		toBeDel.next = toBeDel.next.next;

		return true;
	}
}