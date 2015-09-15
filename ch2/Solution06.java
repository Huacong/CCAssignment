/*
2.6 Palindrome:
	Implement a function to check if a linked list is a palindrome.
*/

/*
Edge case: null, odd, even
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

public class Solution06 {
	static public void main(String[] args) throws IOException {
		ListNode head = input();

		boolean b = palindrome(head);

		output(b);
	}

	/* Helpers */
	static private ListNode input() throws IOException {
		System.out.println("2.6 Palindrome");

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

	static private void output(boolean b) {
		System.out.println();
		System.out.println("The list is palindrome: ");
		System.out.print(b);
		System.out.println();
	}

	/* Solutions */
	/*	Solution 1
		
		Use stack to store the 1st half of the list, compare them backward with the 2nd
		half of the list (forward).

		Time: O(n)
		Space: O(n)
	*/
	static private boolean palindrome(ListNode head) {
		if (head == null || head.next == null)
			return true; 

		Deque<Integer> stack = new ArrayDeque<Integer>();
	    
	    //put the first half of the list into stack
	    ListNode slow = head;
	    ListNode fast = head;
	    while (fast != null && fast.next != null) {
	        stack.push(slow.value);
	        slow = slow.next;
	        fast = fast.next.next;
	    }
	    
	    if (fast != null && fast.next == null) //odd nodes
	        slow = slow.next; //the original slow is the center, move to the begining of 2nd half
	    
	    //start to check
	    while (stack.size() > 0 && slow != null) {
	        if (stack.pop() == slow.value)
	            slow = slow.next;
	        else
	            return false;
	    }
	    
	    if (stack.size() == 0 && slow == null) //nodes from 1st half and 2nd half are matched
	        return true;
	    else
	        return false;
	}
}