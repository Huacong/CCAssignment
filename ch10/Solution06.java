/* 
10.6 Sort Big File
	 Imagine you have a 20 GB file with one string per line. Explain how you 
	 would sort the file.
*/

/* 	Solution:

Analysis:
	Suppose we have x bytes memory for data

	Split the file into 20GB/(x/2) subfiles, so that we could read 2 files at a 
	time, each file is x/2 bytes

	Use merge sort to merge the initial files until all the x/2 bytes files are
	merged into 20GB/x files, each file has x bytes.

	Keep merging file, but every time only read up to x bytes into memory, and 
	write the result to disk before reading remaining bytes.

	Repeat above steps until we get one 20GB file finally.
*/