/* 
6.10 Poison:
	You have 1000 bottles of soda, and exactly one is poisoned. You have 10 test
	strips which can be used to detect poison. A single drop of poison will turn
	the test strip positive permanently. You can put any number of drops on a 
	test strip at once and you can reuse a test strip as many times as you'd like
	(as long as the results are negative). However, you can only run tests once per
	day and it takes seven days to return a result. How would you figure out the 
	poisoned bottle in as few days as possible?

	FOLLOW UP
	Write code to simulate your approach. 
*/

/* Solution
Answer: Binary 
	We have 10 test stips which could be used to represent 0 - 1023 in binary,
	positive as bit 1, negative as bit 0. Give them fix positions to represent
	a 10-bit binary number.

	And we have 1000 bottles of soda, which is less than 1024. 

	Thus we could give every bottle an ID, from 1 - 1000, and put soda of a 
	bottle to the strips that represent its ID.

	Finally, all the positive strips will form an ID that indicates the ID of 
	the bottle that have poison.

	We could figure out the poisoned bottle in seven days.
*/

//simulation
import java.io.*;
import java.util.*;
import java.lang.*;

class Bottle {
	public boolean isPoison = false;
	public int id;

	public Bottle (int id) { this.id = id; }
}

class Strip {
	private static final int resultWaitDay = 7;

	private List<Bottle> dropSamples = new ArrayList<Bottle>();

	public void addDrop(Bottle b) {
		dropSamples.add(b);
	}

	public boolean getResultOnDay (int day) {
		if (day < resultWaitDay) {//at least wait for 7 days, by default start from day 0
			return false;
		}

		for (Bottle b : dropSamples) {
			if (b.isPoison) {
				return true;
			}
		}

		return false;
	}
}

class Input {
	List<Bottle> bottles = new ArrayList<Bottle>();
	List<Strip> strips = new ArrayList<Strip>();
}

public class Solution10 {
	public static void main(String[] args) {
		System.out.println("6.10 Poison");
		test();
	}

	private static void test() {
		Input input = genInput();

		int result = Poison(input.bottles, input.strips);

		System.out.print("Target bottle number: ");
		System.out.println(result);
	}

	private static Input genInput() {
		Input input = new Input();
		for (int i = 1; i <= 1000; i++) {//bottle ID from 1 to 1000
			Bottle b = new Bottle(i);
			input.bottles.add(b);
		}

		int id = (new Random()).nextInt(1000) + 1;
		System.out.println("Poison bottle is: " + id);

		input.bottles.get(id-1).isPoison = true;

		for (int i = 0; i < 10; i++) {
			Strip s = new Strip();
			input.strips.add(s);
		}

		return input;
	}

	/* Solution */
	private static int Poison(List<Bottle> bottles, List<Strip> strips) {
		//set tests for each strip
		for (int i = 0; i < strips.size(); i++) {
			int mask = 1 << i;
			Strip s = strips.get(i);

			//add drops to related binary strips
			for (Bottle b : bottles) {
				if ((b.id & mask) != 0)
					s.addDrop(b);
			}
		}

		//wait for 7 days
		int waitDays = 7;
		int targetNum = 0;
		for (int i = 0; i < strips.size(); i++) {
			if (strips.get(i).getResultOnDay(waitDays))
				targetNum |= (1 << i);
		}

		return targetNum;
	}
}