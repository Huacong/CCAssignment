/* 
5.8 Draw Line:
	A monochrome screen is stored as a single array of bytes, allowing eight
	consecutive pixels to be stored in one byte. The screen has width w, where
	w is divisible by 8 (that is, no byte will be split across rows). The height
	of the screen, of course, can be derived from the length of the array and the
	width. Implement a function that draws a horizontal line from (x1, y) to (x2, y).
	The method signature should look something like:

	drawLine(byte[] screen, int width, int x1, int x2, int y)
*/


import java.io.*;
import java.util.*;

public class Solution08 {
	public static void main(String[] args) {
		System.out.println("5.8 Draw Line");	
		test();
	}

	private static void test() {
		byte[] screen = new byte[8];//4 bit * 16 bit
		int width = 16;

		
		System.out.println("Before:");
		printScreen(screen, width);
		
		int x1 = 2; //[0, 31]
		int x2 = 10;//[0, 31]
		int y = 1;  //[0, 3]
		System.out.format("Draw: x1 = %d, x2 = %d, y = %d\n", x1, x2, y);
		drawLine(screen, width, x1, x2, y);

		System.out.println("After:");
		printScreen(screen, width);
	}

	/* Solution

		Find and set the bits.

		Time:
		Space: O(1)
	*/
	private static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		int row = width / 8 * y;

		//get bytes index that will be set to all 1
		int firstFullByte = x1 / 8;
		int startOffset = x1 % 8;
		if (startOffset != 0) //the first byte is not full byte
			firstFullByte++;

		int lastFullByte = x2 / 8;	
		int endOffset = x2 % 8;		
		if (endOffset != 7) //the last byte is not full byte
			lastFullByte--;

		//set full bytes
		for (int i = firstFullByte; i <= lastFullByte; i++)
			screen[row + i] |= (byte) 0xFF;

		//set bytes that are not full ones
		byte firstByteMask = (byte) (0xFF >>> startOffset);
		byte lastByteMask = (byte) (0xFF << (8 - endOffset - 1));
		
		if ((x1 / 8) == (x2 / 8)) { //within the same byte
			byte mask = (byte) (firstByteMask & lastByteMask);
			screen[row + (x1 / 8)] |= mask;
		} else {
			if (startOffset != 0) {
				screen[row + (x1 / 8)] |= firstByteMask;
			}

			if (endOffset != 7) {
				screen[row + (x2 / 8)] |= lastByteMask;
			}
		}
	}

	private static void printScreen(byte[] screen, int width) {
		int rows = screen.length * 8 / width;
		int cols = width / 8;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(byteToBinary(screen[i * cols + j]));
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	private static String byteToBinary(byte b) {
		StringBuilder sb = new StringBuilder();

		for (int i = 7; i >= 0; i--) {
			if ((b & (1 << i)) == 0)
				sb.append(0);
			else
				sb.append(1);			
		}

		return sb.toString();
	}
}