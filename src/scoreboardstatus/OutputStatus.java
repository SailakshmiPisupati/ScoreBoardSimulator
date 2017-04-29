package scoreboardstatus;

import java.util.ArrayList;

import simulator.ScoreBoard;

public class OutputStatus {
	public static ArrayList<int[]> output = new ArrayList<int[]>();	
	public static int lastClock = -1;
	public static final String instructionOutputFormatString = " %-2s %-2s %-15s  %-4s  %-4s  %-4s  %-4s  %-3s  %-3s  %-3s  %-3s "; // %-6s

	// 0		1  2  3  4  5  6    7    8		 9
	// inst_no, F, I, R, E, W, RAW, WAW, struct, WAR
	private static void showOutput() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Clock Cycle:" + ScoreBoard.clockCycle);
		System.out.println("----------------------Scoreboard -----------------------------------------");
		System.out.println(String.format(instructionOutputFormatString, "#", "#", "Instruction", "IF", "ID", "RD", "EX", "W", "RAW", "WAW", "Struct")); // , "WAR"
		int i=0;
		for (int[] values: output) {
		  System.out.println(String.format(instructionOutputFormatString, i++, values[0], ScoreBoard.instructions.get(values[0]), values[1], values[2], values[3], values[4], values[5], values[6] == 1 ? 'Y' : 'N', values[7] == 1 ? 'Y' : 'N', values[8] == 1 ? 'Y' : 'N')); // , values[9] == 1 ? 'Y' : 'N'
		}
		System.out.println("--------------------------------------------------------------------------");
	}
	
	public static int read(int index, int col) {
		return output.get(index)[col];
	}

	public static void append(int index, int col, int value) {
		if(output.get(index)[col] != 0) throw new Error("Overwrite in output output[" + index + "," + col + "]" + " = " + value);
		appendTo(index, col, value);
	}
	
	public static void appendTo(int index, int col, int value) {
		output.get(index)[col] = value;		
	}
	
	public static void printResults() {
		showOutput();
	}

	public static int add() {
		output.add(new int[9]);
		return ++lastClock;
	}

	public static void init(String string) {
		// create file to store results
	}

}
