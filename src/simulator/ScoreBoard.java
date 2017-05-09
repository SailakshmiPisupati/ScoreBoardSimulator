package simulator;

import java.util.HashMap;
import java.util.TreeMap;

import cache.DCache;
import cache.ICache;
import opcodes.Instruction;
import parser.*;
import pipelinestages.*;
import scoreboardstatus.OutputStatus;

public class ScoreBoard {
	private static final int MAX_CYCLE_COUNT = 250;
	public static TreeMap<Integer, Instruction> instructions = new TreeMap<Integer, Instruction>();
	public static HashMap<String, Integer> labelLocation = new HashMap<String, Integer>();
	public static int clockCycle = 1 ;
	public static boolean halt = false;

	/*Passing command line arguments
	 * inst.txt data.txt config.txt result.txt*/
	public static void main(String[] args) throws Exception {
		InstructionParser.readFile(args[0]);
		DataParser.readFile(args[1]);
		ConfigParser.readFile(args[2]);
		runScoreBoard();
		
	}
	public static void runScoreBoard() throws Exception {
				
		int instruction =1;
		while(clockCycle < MAX_CYCLE_COUNT){	
			Write.execute();
			Execute.execute();
			Read.execute();
			Issue.execute();
			Fetch.execute();
			//OutputStatus.printResults();
			OutputStatus.writeToOutputFile();
			//if(stopScoreBoard()) break;							
			clockCycle++;
			instruction++;
		}
		
	}
	
	public static boolean stopScoreBoard(){
		if(Fetch.instructionCount ==-1)
			return true;
		return false;
	}
}
