/* 
6.2 Basketball
	You have a basketball hoop and someone says that you can play one of two games.
	Game 1: You get one shot to make the hoop.
	Game 2: You get three shots and you have to make two of three shots,
	If p is the probability of making a particular shot, for which values of p should
	you pick one game or the other? 
*/


/* Solution	
Answer:
	The probability of winning game 1
	P1 = p

	The probability of winning game 2
	P2 = C(3, 2) * p^2 * (1-p) + p^3 = -2 * p^3 + 3 * p^2
	
	To make P2 > P1,
	-2 * p^3 + 3 * p^2 > p (0 <= p <= 1)
	-2 * p^2 + 3 * p > 1
	(-2p + 1) * (p - 1) > 0
	0.5 < p < 1

	So for 0.5 < p < 1, pick game 2
	For 0 < p <= 1, pick game 1
	For p = 0, neither of the game can make the hoop
*/
	