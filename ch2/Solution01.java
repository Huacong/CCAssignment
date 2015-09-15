/*
2.1 Remove Dups:
	Write code to remove duplicates from an unsorted linked list.
	
	FOLLOW UP:
	How would you solve this problem if a temporary buffer is not allowed?
*/

/*
Edge case: null
*/

import java.io.*;
import java.util.*;

//Definition of list node
class Node {
	int value;
	Node next;

	void Node(int x) {
		this.value = x;
	}
}

public class Solution01 {
	static public void main(String[] args) throws IOException {
		Node head = input();

		

		output(head);
	}

	/* Helpers */
	static private String input() throws IOException {
		System.out.println("1.1 Is Unique");
		System.out.print("Please input the string: ");

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String s = stdin.readLine();

		Scanner sc = new Scanner(s);
		List<String> list = new ArrayList<String>(2);
		list.add(sc.next());
		list.add(sc.next());
		sc.close();

		return s;
	}

	static private void output(boolean b) {
		System.out.println(b);
	}

	/* Solutions */
	/*	Solution 1

	*/

	/*	Solution 2

	*/
}