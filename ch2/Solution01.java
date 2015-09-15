/*
2.1 Remove Dups:
	Write code to remove duplicates from an unsorted linked list.
	
	FOLLOW UP:
	How would you solve this problem if a temporary buffer is not allowed?
*/

/*
Edge case: null, only 1 node
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

public class Solution01 {
	static public void main(String[] args) throws IOException {
		ListNode head = input();

		//removeDups1(head);
		removeDups2(head);

		output(head);
	}

	/* Helpers */
	static private ListNode input() throws IOException {
		System.out.println("2.1 Remove Dups");

		//read number of nodes
		System.out.print("Please input the number of nodes in the linked list: ");
		Scanner sc = new Scanner(System.in);
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

		return dummy.next;
	}

	static private void output(ListNode head) {
		System.out.println();
		System.out.println("After removing:");

		for (; head != null; head = head.next)
			System.out.print(head.value + " ");

		System.out.println();
	}

	/* Solutions */
	/*	Solution 1
		
		Use hash set to record which value has appear

		Time: O(n)
		Space: O(n)
	*/
	static private void removeDups1(ListNode head) {
		if (head == null)
			return;

		Set<Integer> set = new HashSet<Integer>();
		ListNode pre = head;

		for (; head != null; head = head.next) {
			if (set.contains(head.value)) {
				pre.next = head.next;
			} else {
				set.add(head.value);
				pre = head;
			}
		}
	}

	/*	Solution 2
		
		Whenever the first time a value appear, remove all other dup nodes behind

		Time: O(n^2)
		Space: O(1)
	*/
	static private void removeDups2(ListNode head) {
		if (head == null)
			return;

		ListNode pre = head;
		ListNode cur = head;
		for (; head != null; head = head.next) { //iterate all nodes
			for (cur = head.next; cur != null; cur = cur.next) { //remove all dups appear after head
				if (head.value == cur.value) //skip dups
					pre.next = cur.next;
				else
					pre = cur;
			}
		}
	}
}