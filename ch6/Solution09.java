/* 
6.9 100 Lockers:
	There are 100 closed lockers in a hallway. A man begins by opening all 100 
	lockers. Next, he closes every second lovker. Then on his third pass, he 
	toggles every third locker (closes it if it is open or opens it if it is 
	closed). This process continues for 100 passes, such that on each pass i,
	the man toggles every ith locker. After 100th pass in the hallway, in which
	he toggles only locker #100, how many lockers are open?
*/


/* Solution
Answer:
	For a locker, if it is toggled odd times, it will be open, otherwise, it will
	be closed.

	And every locker will be toggled at ith pass, where i is the factor of 
	the number(id) of the locker.

	So if the number of locker has even factors, it will be open finally, 
	otherwise it will be closed.

	For number 1 - 100, every factor has a pair factor, such as (1, 8), (2, 4) 
	for nubmer 8, only the numbers that are the square of factors will have odd 
	factors.

	Those numbers are 1*1, 2*2, 3*3, ..., 10*10. So there will be 10 lockers that 
	are open after 100 passes.
*/