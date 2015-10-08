/* 
6.1 The Heavy Pill:
	You have 20 bottles of pills. 19 bottles have 1.0 gram pills, but one has 
	pills of weight 1.1 gram. Given a scale that provides an exact measurement,
	how would you find the heavy bottle? You can only use the scale once.
*/


/* Solution
Assumptions:
	Each bottle has more than 19 pills. 

Answer: 
	Give an id to each bottle, 1 - 20.

	Take i pills from bottle i, except the 20th.

	The total weight of this 19 bottles should be w = 1 + 2 + 3 + ... + 19 = 190, 
	if all of them have 1.0 gram pills

	If the total weight = 190, the 20th bottle is the one with 1.1 gram pills

	Otherwise, the id of the target bottle i = (w - 190) / (1.1 - 1.0), since 
	the ith bottle contribulte i 1.1 gram pills and contribute i * 0.1 gram 
	to the total weight

	In this case, we only use the scale once.
*/
	



}