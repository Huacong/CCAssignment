/* 
6.6 Blue-Eyed Island:
	A bunch of people are living on an island, when a visitor comes with a strange
	order: all blue-eyed people must leave the island as soon as possible. There 
	will be a flight out at 8:00 pm every evening. Each person can see everyone
	else's eye color, but they do not know their own (not is anyone allowed to tell
	then). Additionally, they do not know how many people have blue eyes, although 
	they do know that at least one person does. How many days will it take the
	blue-eyed people to leave? 
*/


/* Solution
Answer:
	Solve the problem by deduction

	a. One blue-eyed person
		If there is only one blue-eyed person, he will find that no one else has 
		blue eyes. Since at least one person has blue eyes, the blue-eyed person
		will know that he is the one with blue eyes, and leave at the 1st day.

	b. Two blue-eyed people
		The people with blue eyes will find out that there is one blue-eyed 
		person, except themself. They will wait for one day, and if there is only
		one blue-eyed person, the case above, there will not be any blue-eyed
		person at the 2nd day. But if they saw each other at the 2nd day, they
		will know that themselves are blue-eyed too. Thus they will leave at
		the 2nd day.

		For other people, they will find out there are 2 blue-eyed people, 
		except themselves. This will be cover below.

	c. N blue-eyed people
		Through above deduction we can see that a person with blue eyes can 
		find out there are N-1 people with blue eyes, excepet themselves. 
		And if there are only N-1 blue-eyed people, they will leave at N-1th
		day, at the same time. But if the people are still there at Nth day,
		then they know there are totally N blue-eyed people, including 
		themselves, and leave the island at the Nth day.

Conclusion:
		N blue-eyed people will leave at the Nth day together
*/
