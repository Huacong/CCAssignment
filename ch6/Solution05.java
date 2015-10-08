/* 
6.5 Jugs of Water:
	You have a five-quart jug, a three-quart jug, and an unlimited supply of water
	(but no measuring cups). How would you come up with exactly four quarts of 
	water? Note that the jugs are oddly shaped, such that filling up exactly "half"
	of the jug would be impossible. 
*/


/* Solution 
Answer:
	Use the following steps to get the 4 quarts water:
	Container1 (3-quart)	Container2 (5-quart)	Action
			0						 5				Fill 5 quarts water to c2
			3						 2				Move 3 quarts water from c2 to c1
			0						 2				Drop water in c1
			2						 0				Move 2 quarts water from c2 to c1
			2						 5				Fill 4 quarts water to c2
			3						 4				Move 1 quart water from c2 to c1

	Finally we get 4 quarts of water in Container2
*/
	