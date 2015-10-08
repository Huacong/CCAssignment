/* 
5.5 Debugger:
	Explain what the following code does: ((n & (n-1)) == 0) 
*/


/* Solution
Answer:
	n == 0 or n only has 1 bit of one

	n-1 will make the most right zeros block become ones, and the most right 
	one become 0. Only if n is 0 or has only 1 bit of one will make (n & (n-1)) 
	== 0
*/