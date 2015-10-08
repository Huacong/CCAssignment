/* 
6.7 The Apocalypse:
	In the new post-apocalyptic world, the world queen is desperately concerned
	about the birth rate. Therefore, she decrees that all families should ensure
	that they have one girl or else they face massive fines. If all families abide
	by this policy - that is, they have continue to have children until they have
	one girl, at which point they immediately stop - what will the gender ratio of
	the new generation be? (Assume that the odds of someone having a boy or a girl
	on any given pregnancy is equal.) Solve this out logically and then write a 
	computer simulation of it.
*/


/* Solution 
Answer:
	Accoding to the description, the odds of having a boy or a girl on any 
	pregnancy is 1/2.

	Use B for boy and G for girl to represent the birth sequence for a family,
	i.e., G for family that only has one girl, BG for family that has a boy
	first then a girl.

	The probability of the sequence for a family is
	0 boy, G, p0 = 1/2
	1 boy, BG, p1 = (1/2)^2
	2 boy, BBG, p2 = (1/2)^3
	...
	n boy, B...BG, pn = (1/2)^(n+1)

	The expectation of the number of boys of a family is
	0 * 1/2 + 1 * (1/2)^1 + ... + n * (1/2)^(n+1)

	When n -> inf, the sum -> 1, which means the expectation of the number 
	of boys of a family is 1, and this equals to the number of girl of a 
	family.

	In other words, the ratio of boys and girls in each family is 1 : 1.
	Thus the overall gender ratio of the world will be 1 : 1.

Explaination:
	The policy doesn't change the odds of having boy or girl, thus it cannot 
	affect the ratio of gender. Although every family will have exactly 1 
	girl, some family won't have boys and some will have multiple boys, which 
	balance the effect of the policy.

	Since we care about the whole world, we could append the birth sequence 
	above to a big string. And the probability of append a new B or G is the 
	same. Thus there will be equal number of B and G in the string.
*/

//Simulation
import java.io.*;
import java.util.*;
import java.lang.*;

public class Solution07 {
	//simulate the number of families in the world
	private static final int numFamily = 1000;

	public static void main(String[] args) {
		System.out.println("6.7 The Apocalypse");
		test();
	}

	private static void test() {
		int girls = numFamily; //every family has exactly 1 girl
		int boys = 0;

		//get number of boys from every family
		for (int i = 0; i < numFamily; i++)
			boys += getBoysOfOneFamily();

		double ratio = (double)boys / girls; //should close to 1.0
		System.out.println("Families: " + numFamily);
		System.out.println("Ratio: boys/girls = " + ratio);
	}

	//Randomly generate the amount of boys for a family
	private static int getBoysOfOneFamily() {
		Random random = new Random();
		int boys = 0;

		while(random.nextBoolean()) { //true for boy, false for girl
			boys++;
		}

		return boys;
	}
}
