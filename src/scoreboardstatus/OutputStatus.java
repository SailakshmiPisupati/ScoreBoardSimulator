package scoreboardstatus;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import pipelinestages.Fetch;
import cache.DCache;
import cache.ICache;
import simulator.ScoreBoard;

public class OutputStatus {
	public static ArrayList<int[]> output = new ArrayList<int[]>();	
	public static int lastClock = -1;
	public static final String outputFormat = " %-2s %-2s %-20s  %-4s  %-4s  %-4s  %-4s  %-3s  %-3s  %-3s  %-3s "; // %-6s

	private static void showOutput() {
		System.out.println("--------------------------------------------------------------------------");
		System.out.println("Clock Cycle:" + ScoreBoard.clockCycle);
		System.out.println("----------------------Scoreboard -----------------------------------------");
		System.out.println(String.format(outputFormat, "Sr", "IC", "Instruction", "IF", "IS", "RD", "EX", "W", "RAW", "WAW", "Struct")); // , "WAR"
		int i=0;
		for (int[] values: output) {
		  System.out.println(String.format(outputFormat, i++, values[0], ScoreBoard.instructions.get(values[0]), values[1], values[2], values[3], values[4], values[5], values[6] == 1 ? 'Y' : 'N', values[7] == 1 ? 'Y' : 'N', values[8] == 1 ? 'Y' : 'N')); // , values[9] == 1 ? 'Y' : 'N'
		}
		System.out.println("--------------------------------------------------------------------------");
		int icachehits = Collections.frequency(Fetch.icachehits.values(), true);
		System.out.println("Total number of access requests for instruction cache "+ ICache.ICacheAccessedCount);
		System.out.println("Number of instruction cache hits: "+icachehits);
		System.out.println("Total number of access requests for data cache "+DCache.DCacheAccessCount);
		System.out.println("Number of data cache hits: "+DCache.DCachehit);
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
	public static String filename= "result.txt";
	public static FileWriter fw;//the true will append the new data

	public static void writeToOutputFile()throws IOException {
		fw = new FileWriter(filename,false); 
		fw.write(String.format(outputFormat, "Sr", "IC", "Instruction", "IF", "IS", "RD", "EX", "W", "RAW", "WAW", "Struct")+"\n");//appends the string to the file
		int i=0;
		for (int[] values: output) {
		  fw.write(String.format(outputFormat, i++, values[0], ScoreBoard.instructions.get(values[0]), values[1], values[2], values[3], values[4], values[5], values[6] == 1 ? 'Y' : 'N', values[7] == 1 ? 'Y' : 'N', values[8] == 1 ? 'Y' : 'N')+"\n");
		}
		int icachehits = Collections.frequency(Fetch.icachehits.values(), true);
		fw.write("--------------------------------------------------------------------------\n");
		fw.write("Total number of access requests for instruction cache "+ ICache.ICacheAccessedCount+"\n");
		fw.write("Number of instruction cache hits: "+icachehits+"\n");
		fw.write("Total number of access requests for data cache "+"\n");
		fw.write("Number of data cache hits: "+ICache.hits+"\n");
		fw.close();
	}

}
