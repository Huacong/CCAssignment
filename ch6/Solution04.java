/* 
6.4 Ants on a Triangle:
	There are three ants on different vertices of a triangle. What is the 
	probability of collision (between any two or all of them) if they start
	walking on the sides of the triangle? Assume that each ant randomly picks
	a direction, with either direction being equally likely to be chosen, and
	that they walk at the same speed.

	Similarly, find the probability of collision with n ants on an n-vertex
	polygon.
*/


/* Solution 
Answer:
	The probability of the ant picking one direction is 1/2 for any polygon (
	there are only two directions).

	In triangle, the probability of not collision, i.e. all ants pick the same
	direction is 
							1 * (1/2)^2 = 1/4
	It means the 1st ant pick any direction, which has probability 1, and all
	the other ants should pick the same direction, which has probability 1/2.
	Since all of their decisions are independent, we get the combined probability
	by multiply them.

	Thus the probability of collision is
							1 - 1/4 = 3/4

	For an n-vertex polygon, the probability of not collision is
							1 * (1/2)^(n-1)

	Thus the probability of collision is
							1- (1/2)^(n-1)
*/
	