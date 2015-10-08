/* 
5.2 Binary to String:
	Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a 
	double, print the binary representation. If the number cannot be represented 
	accurately in binary with at most 32 characters, print "ERROR".
*/


import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution02 {
	public static void main(String[] args) {
		System.out.println("5.2 Binary to String");
		test();
	}

	private static void test() {
		double num = 0.625;
		String res = BinarytoString(num);
		System.out.println(num + " " + res);

		num = 0.3;
		res = BinarytoString(num);
		System.out.println(num + " " + res);
	}

	/* Solution

		A decimal real number can be represent as (1/2)^x1 + (1/4)^x2 + ... + 
		(1/2^n)^xn, where x is 0 or 1.

		Thus multiply the number by 2 every time, and the integer part is xn.

		Time: O(1), 32 bits
	*/
	private static String BinarytoString(double num) {
		StringBuilder sb = new StringBuilder("0.");

		int i = 0;
		for (; i < 32 && num != 0; i++) {
			num *= 2;

			if (num >= 1) {
				sb.append(1);
				num -= 1;
			} else {
				sb.append(0);
			}
		}

		if (i == 32 && num != 0)
			return "ERROR";

		return sb.toString();
	}
}