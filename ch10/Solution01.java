/* 
10.1 Sorted Merge
	 You are given two sorted arrays, A and B, where A has a large enough buffer 
	 at the end to hold B. Write a method to merge B into A in sorted order.
*/

import java.lang.*;
import java.io.*;
import java.util.*;

class Input {
	public int[] A;
	public int[] B;
	public int m;
	public int n;
}

public class Solution01 {
	public static void main(String[] args) {
		System.out.println("10.1 Sorted Merge");

		test();
	}

	private static void test() {
		Input input = generateInput();

		//output A
		System.out.println("Before sorted");

		System.out.print("A: ");
		for (int i = 0; i < input.m; i++)
			System.out.print(input.A[i] + " ");

		System.out.println();

		//output B
		System.out.print("B: ");
		for (int i = 0; i < input.n; i++)
			System.out.print(input.B[i] + " ");

		System.out.println();

		//sort
		sortedMerge(input.A, input.m, input.B, input.n);

		//output result
		System.out.println("After sorted");

		for (int i = 0; i < input.m + input.n; i++)
			System.out.print(input.A[i] + " ");

		System.out.println();
	}

	private static Input generateInput() {
		int m = 10;
		int n = 5;
		Input input = new Input();

		//generate element for A and B in alternative pattern, 
		//i.e. A contains odd number, B contains even number
		input.m = m;
		input.A = new int[m+n]; //reverse enough buffer for B
		for (int i = 0; i < m; i++)
			input.A[i] = 2 * i + 1; //odd number

		input.n = n;
		input.B = new int[n];
		for (int i = 0; i < n; i++)
			input.B[i] = 2 * i; //even number

		return input;
	}

	/* Solution */

	/* Solution
	Analysis:
		Since A has enough buffer to hold B, start from the end of two arrays,
		pick a largest element at a time and put it to the target place in A.
		The initial target place is A[m+n], where m is A's actual length and n
		is B's actual length. Since we reserve enough space for the result, the
		original element in A will not be overwritten.

	Assumptions:
		A has enough buffer to hold B

		Time: O(n)
		Space: O(1), not includes input array

	Paras:
		A, B array to be merged
		m actual length of A
		n actual length of B
	*/
	private static void sortedMerge(int[] A, int m, int[] B, int n) {
		if (A == null || B == null || (A.length < m + n))
			return;

		int indexTotal = m + n - 1;
		int indexA = m - 1;
		int indexB = n - 1;
		for (; indexA >= 0 && indexB >= 0; indexTotal--) {
			if (A[indexA] >= B[indexB]) {
				A[indexTotal] = A[indexA];
				indexA--;
			} else {
				A[indexTotal] = B[indexB];
				indexB--;
			}
		}

		//if B reaches end first, no need to process A
		//if A reaches end first, move all the remaining element in B to A
		for (; indexTotal >= 0 && indexB >= 0; indexTotal--, indexB--)
			A[indexTotal] = B[indexB];
	}
}