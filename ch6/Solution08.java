/* 
6.8 The Egg Drop Problem:
	There is a building of 100 floors. If an egg drops from the Nth floor or above,
	it will break. If it's dropped from any floor below, it will not break. You're
	given two eggs. Find N, while minimizing the number of drops for the worst case. 
*/


/* Solution
Answer:
	Given two eggs, the first one is to find out the range (lower and upper bound) 
	among which the eggs will break. The second egg is to find out the exact
	floor that the egg will break.

	Suppose the 1st egg jump 10 floors at a time, and it doesn't break at 10th, 
	but break at 20th. Than the range of the floors is limited to (10, 20), and 
	the 2nd egg could start from 11, iterate through 19 to find out the exact
	floor.

	In the above case, the worst case of egg 1 is 10, and egg 2 is 10. Thus the 
	worst case in total is 19. However, the worst case of egg 2 doesn't change 
	along with the drop times of egg 1. Suppose egg 1 already find out the 
	range, in its 1st, 2nd, 3rd, ... times drop, the total worst case become 
	11, 12, 13, .... 

	To minimize the total worst case, we might balance the drop times of egg 1
	and egg 2, so that the worst case keeps a constant for all situation. For 
	example, egg 1 jump x, x-1, x-2, ... floors every time, thus its drop times
	is 1, 2, 3, .... And the worst case of egg 2 becomes x-1, x-2, x-3, ... times.
	The total worst case will always be x. In this way, we balance the two 
	components in the sum formula and keep the sum as a constant, thus minimize it.
	
	Accoding to analysis above, we could havd
						x + (x-1) + (x-2) + ... + 1 = 100
						x = 13.65

	If we set x = 13, the floors for egg 1 are
					13, 25, 36, 46, 55, 63, 70, 76, 81, 85, 88, 90, 91
	Before 91, the total worst case is 13. But after that, we need to recompute x,
	and the floors for egg 1 may go like
					95, 98, 100
	For the last 3 floor, the worst case is 17. Thus the overall worst case is 13

	If we set x = 14, the floors for egg 1 are
						14, 27, 39, 50, 60, 69, 77, 84, 90, 95, 99
	And the worst case will always be 14.

	To minimize the worst case, we pick x = 14.
	However, we may want to pick x = 13 when we want to minimize the averge case.
*/