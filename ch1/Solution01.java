/* 
1.1 Is Unique: 
	Implement an algorithm to determine if a string has all unique characters.
	What if you cannot use additional data structures?

	Here we assume that "no additional data structures" means O(1) space 
	complexity.
*/

public class Solution01 {
	static public void main(String[] args) {
		String s = input();

		boolean b = isUnique1(s);
		//boolean b = isUnique2(s);
		//boolean b = isUnique3(s);

		output(b);
	}

	/* helpers */
	static private String input () {
		System.out.println("1.1 Is Unique");
		System.out.print("Please input the string: ");

		BufferReader stdin = new BufferReader(new InputSteamReader(System.in));
		String s = stdin.readLine();

		return s;
	}

	static private void output (boolean b) {
		System.out.println(b);
	}

	/* solutions */
	/*	Solution 1
		Assumption: Only have basic ASCII chars (0-127)

	   	Use bit vector to check if the chars already exists.

	   	Time: O(n)
	   	Space: O(1)
	*/
	static private boolean isUnique1 (String s) {
		if (s == null || s.length() == 0)
			return true;

		char[] exists = new char[128]; //default values are false

		for (int i = 0; i < s.length(); i++) {
			if (exists[s.charAt(i)]) //exists
				return false;
			else
				exists[s.charAt(i)] = true;
		}

		return true;
	}
}