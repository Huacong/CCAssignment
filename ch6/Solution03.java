/* 
6.3 Dominos:
	There is an 8*8 chessboard in which two diagonally opposite corners have been
	cut off. you are given 31 dominos, and a single domino can cover exactly two
	squares. Can you use the 31 dominos to cover the entire board? Prove your 
	answer (by providing an example or showing why it's impossible).
*/


/* Solution 
Answer:
	31 dominos cannot cover the entire board.

Proof:
	Mark the whole chessboard with two colors, say white and black. Every adjacent 
	grid cannot have the same color. And finally every color has 32 grids.

	Since there are even number of grids in one row/column, for the adjacent 
	corners, if one of them has odd index, the other one must have even index. And
	in one row/column, only the grids that have same kind of index (odd/even) will
	have the same color. Thus adjacent corner will have different colors.

	Since adjacent corner will have different colors, the diagonally opposite corners
	will have the same color.

	After cutting off the two diagonally opposite corners, there will be 30 grids 
	of one color, 32 grids of the other color.

	A domino can only cover two adjacent grids, i.e., two colors at the same time.
	Since the number of girds of different colors are not the same, the given dominos
	cannot cover the entire chessboard,
*/
