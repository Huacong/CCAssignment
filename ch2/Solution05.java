/*
2.5 Sum Lists:
	You have two numbers represented by a linked list, where each node contains
	a single digit. The digits are stored in reverse order, such that the 1's 
	digit is at the head of the list. Write a function that adds the two numbers
	and returns the sum as a linked list.

	EXAMPLE:
	Input: 	(7->1->6) + (5->9->2). That is 617 + 295
	Output:	2->1->9.That is, 912.

	FOLLOW UP:
	Suppose the digits are stored in forward order. Repeat the above problem.

	EXAMPLE:
	Input:	(6->1->7) + (2->9->5). That is, 617 + 295
	Output: 9->1->2. That is, 912.
*/

/*
Edge case: null, l1.length > l2.length
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

public class Solution05 {
	static public void main(String[] args) throws IOException {
		InputArgs inputArgs = input();

		//ListNode result = sumLists_backward(inputArgs.l1, inputArgs.l2);
		ListNode result = sumLists_forward(inputArgs.l1, inputArgs.l2);

		output(result);
	}

	/* Helpers */
	static private InputArgs input() throws IOException {
		System.out.println("2.5 Sum Lists");

		//read number of nodes for list 1
		System.out.println();
		System.out.print("Please input the number of nodes in the linked list 1: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		//read linked list
		System.out.println();
		System.out.print("Please input value of nodes for list 1 (seperate by space, e.g., 1 2 3): ");
		
		ListNode dummy1 = new ListNode(0);
		ListNode pre = dummy1;
		for (int i = 0; i < n; i++) {
			ListNode cur = new ListNode(sc.nextInt());
			pre.next = cur;
			pre = cur;
		}

		//read number of nodes for list 2
		System.out.print("Please input the number of nodes in the linked list 2: ");
		n = sc.nextInt();

		//read linked list
		System.out.print("Please input value of nodes for list 2 (seperate by space, e.g., 1 2 3): ");
		
		ListNode dummy2 = new ListNode(0);
		pre = dummy2;
		for (int i = 0; i < n; i++) {
			ListNode cur = new ListNode(sc.nextInt());
			pre.next = cur;
			pre = cur;
		}

		return new InputArgs(dummy1.next, dummy2.next);
	}

	static private void output(ListNode result) {
		System.out.println();
		System.out.println("Result: ");
		for (; result != null; result = result.next)
			System.out.print(result.value + " ");
		System.out.println();
	}

	/* Solutions */
	/*	Solution 1
		
		Sum lists that store digits in backward order

		Time: O(n)
		Space: O(1)
	*/
	static private ListNode sumLists_backward(ListNode l1, ListNode l2) {
		ListNode dummy = new ListNode(0);
		ListNode pre = dummy;
		int carry = 0;

		//sum lists
		while (l1 != null || l2 != null) {
			if (l1 != null) {
				carry += l1.value;
				l1 = l1.next;
			}

			if (l2 != null) {
				carry += l2.value;
				l2 = l2.next;
			}

			pre.next = new ListNode(carry % 10);
			pre = pre.next;
			carry /= 10;
		}

		if (carry != 0) //both lists reach the end, but still have carry bit
			pre.next = new ListNode(carry);

		return dummy.next;
	}

	/*	Solution 2
		
		Sum lists that store digits in forward order, use stack to add digits backward

		Time: O(n)
		Space: O(n)
	*/
	static private ListNode sumLists_forward(ListNode l1, ListNode l2) {
		//push l1
		Deque<Integer> stack1 = new ArrayDeque<Integer>();
		while (l1 != null) {
			stack1.push(l1.value);
			l1 = l1.next;
		}

		//push l2
		Deque<Integer> stack2 = new ArrayDeque<Integer>();
		while (l2 != null) {
			stack2.push(l2.value);
			l2 = l2.next;
		}

		ListNode dummy = new ListNode(0);
		int carry = 0;

		//sum lists
		while (!stack1.isEmpty() || !stack2.isEmpty()) {
			if (!stack1.isEmpty())
				carry += stack1.pop();

			if (!stack2.isEmpty())
				carry += stack2.pop();

			ListNode cur = new ListNode(carry % 10);
			cur.next = dummy.next;
			dummy.next = cur;
			carry /= 10;
		}

		if (carry != 0) {//both lists reach the end, but still have carry bit
			ListNode cur = new ListNode(carry);
			cur.next = dummy.next;
			dummy.next = cur;
		}

		return dummy.next;
	}
}