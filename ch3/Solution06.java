/* 
3.6 Animal Shelter:
	An animal shelter, which holds only dogs and cats, operates on a strictly 
	"first in, first out" basis. People must adopt either the "oldest" (based
	on arrival time) of all animals at the shelter, or they can select whether
	they would prefer a dog or a cat (and will receive the oldest animal of 
	that type). They cannot select which specific animal they would like. Create
	the data structures to maintain this system and implement operations such
	as enqueue, dequeueAny, dequeueDog, and dequeueCat. You may use the built-in
	LinkedList data structure.
*/

import java.io.*;
import java.util.*;

/*	Solution:

	Use two seperate queues for dogs and cats. And use timestamp to get the oldest
	animal for dequeueAny

	Time: O(1) for enqueue and dequeue
	Space: O(n) for storing timestamp
*/

enum AnimalType {cat, dog};

class Animal {
	public long timestamp;
	public AnimalType type;
	public String name;

	public Animal (AnimalType type, String name) {
		this.type = type;
		this.name = name;
	}

	public boolean isOldest(Animal animal) {
		return this.timestamp <= animal.timestamp;
	}
}

class AnimalShelter {
	Queue<Animal> dogs = new LinkedList<Animal>();
	Queue<Animal> cats = new LinkedList<Animal>();

	public void enqueue(Animal animal) {
		animal.timestamp = System.currentTimeMillis();

		if (animal.type == AnimalType.cat)
			cats.offer(animal);
		else
			dogs.offer(animal);
	}

	//return null if no animals
	public Animal dequeueAny() {
		//no dogs
		if (dogs.isEmpty())
			return dequeueCat();

		//no cats
		if (cats.isEmpty())
			return dequeueDog();

		//pick the oldest one
		Animal dog = dogs.peek();
		Animal cat = cats.peek();
		if (dog.isOldest(cat))
			return dequeueDog();
		else
			return dequeueCat();
	}

	//return null if no dogs
	public Animal dequeueDog() {
		return dogs.poll();
	}

	//return null if no cats
	public Animal dequeueCat() {
		return cats.poll();
	}
}

public class Solution06 {
	public static void main(String[] args) {
		System.out.println("3.6 Animal Shelter");
		test();
	}

	private static void test() {
		AnimalShelter animalShelter = new AnimalShelter();

		animalShelter.enqueue(new Animal(AnimalType.dog, "dog A"));
		animalShelter.enqueue(new Animal(AnimalType.dog, "dog B"));
		animalShelter.enqueue(new Animal(AnimalType.dog, "dog C"));
		animalShelter.enqueue(new Animal(AnimalType.cat, "cat D"));
		animalShelter.enqueue(new Animal(AnimalType.cat, "cat E"));
		animalShelter.enqueue(new Animal(AnimalType.cat, "cat F"));

		System.out.println("Oldest animal: " + animalShelter.dequeueAny().name);
		System.out.println("Oldest dog: " + animalShelter.dequeueDog().name);
		System.out.println("Oldest cat: " + animalShelter.dequeueCat().name);
	}
}