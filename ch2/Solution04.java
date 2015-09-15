/*
2.4 Partition:
	Write code to partition a linked list around a value x, such that all nodes
	less than x come before all nodes greater than or equal to x. If x is
	contained within the list, the values of x only need to be after the elements
	less than x.

	EXAMPLE:
	Input:	3->5->8->5->10->2->1 [partition = 5]
	Output: 3->1->2->10->5->5->8
*/

/*
Edge case: null
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
	int x;
	ListNode head;

	InputArgs(int x, ListNode head) {
		this.x = x;
		this.head = head;
	}
}

public class Solution04 {
	static public void main(String[] args) throws IOException {
		InputArgs inputArgs = input();

		ListNode result = partition(inputArgs.x, inputArgs.head);

		output(result);
	}

	/* Helpers */
	static private InputArgs input() throws IOException {
		System.out.println("2.4 Partition");

		//read K
		System.out.print("Please input the value for partition: ");
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
		for (int i = 0; i < n; i++) {
			ListNode cur = new ListNode(sc.nextInt());
			pre.next = cur;
			pre = cur;
		}

		return new InputArgs(x, dummy.next);
	}

	static private void output(ListNode result) {
		System.out.println();
		System.out.println("After partition: ");
		
		for (; result != null; result = result.next)
			System.out.print(result.value + " ");

		System.out.println();
	}

	/* Solutions */
	/*	Solution 1
		
		Append all the nodes less than partition x to a list, and append others to another list,
		finally link these two lists. This is a stable partition, which means the nodes will
		preserve the original relative order of the nodes in each of the two partitions.

		Time: O(n)
		Space: O(1)
	*/
	static private ListNode partition(int x, ListNode head) {
		if (head == null)
			return null;

		//init
		ListNode leftDummy = new ListNode(0);
		ListNode rightDummy = new ListNode(0);
		ListNode left = leftDummy;
		ListNode right = rightDummy;

		//partition nodes
		while (head != null) {
			if (head.value < x) {
				left.next = head;
				left = head;
			} else { //head.value >= x
				right.next = head;
				right = head;
			}

			head = head.next;
		}

		right.next = null;
		left.next = rightDummy.next;

		return leftDummy.next;
	}
}