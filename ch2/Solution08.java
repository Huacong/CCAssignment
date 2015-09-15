/*
2.8 Loop Detection: 
	Given a circular linked list, implement an algorithm that returns the node
	at the beginning of the loop.

	DEFINITION:
	Circulat linked list: A (corrupt) linked list in which a node's next pointer 
	points to an earlier node, so as to make a loop in the linked list.

	EXAMPLE:
	Input:	A->B->C->D->E->C [the same C as earlier]
	Output: C
*/

/*
Edge case: null, no loop
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

public class Solution08 {
	static public void main(String[] args) throws IOException {
		ListNode head = input();

		ListNode loopBeginning = loopDetection(head);

		output(loopBeginning);
	}

	/* Helpers */
	static private ListNode input() throws IOException {
		System.out.println("2.8 Loop Detection");

		//read number of nodes
		System.out.print("Please input the number of nodes in the linked list: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		//read linked list
		System.out.println();
		System.out.print("Please input value of nodes (seperate by space, e.g., 1 2 3): ");
		
		ListNode dummy = new ListNode(0);
		ListNode pre = dummy;
		Map<Integer, ListNode> map = new HashMap<Integer, ListNode>();
		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			ListNode cur = new ListNode(x);

			map.put(x, cur); //only the last node with same value will be stored

			pre.next = cur;
			pre = cur;
		}

		//read the begin of loop
		System.out.println();
		System.out.print("Please input the value of the begin of loop (if there is " + 
			"a loop, this value should be some value appear before, otherwise you can input any value): ");
		int loopBeginning = sc.nextInt();

		//if loop doesn't exist, the tail will point to null, otherwise the node has value loop
		pre.next = map.get(loopBeginning);

		return dummy.next;
	}

	static private void output(ListNode begin) {
		System.out.println();
		System.out.println("The beginning of the loop is: ");
		if (begin != null)
			System.out.print(begin.value);
		else 
			System.out.print("null");
		System.out.println();
	}

	/* Solutions */
	/*	Solution 1
		
		Use two pointers, Slow and a Fast, to check if there is a loop.
		Slow walks 1 step every time.
		Fast walks 2 steps every time.
		They will meet inside the loop.

		After meeting, suppose Slow walk S steps, thus the Fast one walks 2S steps.
		The length of the loop is R
		The distance between the head of the list and the beginning of the loop is A
		The distance between the meeting point and the beginning of the loop is X (beginning 
		-> meeting point)
		
		2S = S + nR -> S = nR
		S = A + X -> A = S - X = (n-1)R + (R-X), R - X is the distance between Fast and the 
		beginning (meeting point -> beginning).

		Thus after meeting, set Slow to the head of the list, let both Slow and Fast walk 1
		step everytime. The new meeting point is the beginning.

		Time: O(n)
		Space: O(1)
	*/
	static private ListNode loopDetection(ListNode head) {
		if (head == null)
			return null;

		ListNode slow = head;
		ListNode fast = head;

		//find the meeting point
		while (fast != null && fast.next != null) {
			slow = slow.next;
			fast = fast.next.next;

			if (slow == fast)
				break;
		}

		if (fast == null || fast.next == null) //not stop due to collision
			return null;
		
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}

		return slow;
	}
}