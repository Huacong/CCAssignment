/*
2.2 Return Kth to Last:
	Implement an algorithm to find the kth to last element of a singly linked 
	list.
*/

/*
Edge case: null, only 1 node, k > length of the list
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
	int k;
	ListNode head;

	InputArgs(int k, ListNode head) {
		this.k = k;
		this.head = head;
	}
}

public class Solution02 {
	static public void main(String[] args) throws IOException {
		InputArgs inputArgs = input();

		ListNode result = kthToLast(inputArgs.k, inputArgs.head);

		output(result);
	}

	/* Helpers */
	static private InputArgs input() throws IOException {
		System.out.println("2.2 Return Kth to Last");

		//read K
		System.out.print("Please input K: ");
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();

		//read number of nodes
		System.out.println();
		System.out.print("Please input the number of nodes in the linked list: ");
		int n = sc.nextInt();

		//read linked list
		System.out.println();
		System.out.print("Please input value of nodes (seperate by space, e.g., 1 2 3): ");
		
		ListNode dummy = new ListNode(0);
		ListNode pre = dummy;
		for (int i = 0; i < n; i++) {
			ListNode cur = new ListNode(sc.nextInt());
			pre.next = cur;
			pre = cur;
		}

		return new InputArgs(k, dummy.next);
	}

	static private void output(ListNode result) {
		System.out.println();
		System.out.println("Kth to the last is: ");
		if (result != null)
			System.out.print(result.value);
		System.out.println();
	}

	/* Solutions */
	/*	Solution 1
		
		Use two pointers, the previous one points the kth to the last, while 
		the later one points the tail.

		Time: O(n)
		Space: O(1)
	*/
	static private ListNode kthToLast(int k, ListNode head) {
		if (head == null)
			return null;

		//count the length of the list
		int len = 0;
		for (ListNode node = head; node != null; node = node.next)
			len++;

		//check if k is valid
		if (k > len)
			return null;

		ListNode pre = head;
		int count = 0;
		for (; head != null; head = head.next) {
			if (count >= k) //pre is kth to the head
				pre = pre.next;
			else
				count++;
		}

		return pre;
	}
}