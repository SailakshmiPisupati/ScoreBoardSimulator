package simulator;

import java.util.HashMap;
import java.util.TreeMap;

import opcodes.Instruction;
import parser.*;
import pipelinestages.*;
import scoreboardstatus.OutputStatus;

public class ScoreBoard {
	private static final int MAX_CYCLE_COUNT = 40;
	public static TreeMap<Integer, Instruction> instructions = new TreeMap<Integer, Instruction>();
	public static HashMap<String, Integer> label_map = new HashMap<String, Integer>();
	public static int clockCycle =13 ;
	public static boolean halt = false;

	/*Passing command line arguments
	 * inst.txt data.txt config.txt result.txt*/
	public static void main(String[] args) throws Exception {
		InstructionParser.readFile(args[0]);
		DataParser.readFile(args[1]);
		ConfigParser.readFile(args[2]);
		runopcodes();
		
	}
	public static void runopcodes() throws Exception {
				
		int instruction =1;
		while(clockCycle < MAX_CYCLE_COUNT){	
			Write.execute();
			Execute.execute();
			Read.execute();
			Issue.execute();
			Fetch.execute();
			OutputStatus.printResults();
			if(to_stop()) break;							
			clockCycle++;
			instruction++;
		}
		
	}
	
	private static boolean all_units_stopped() {
		return Write.writeQueue.isEmpty()&& Read.readQueue.isEmpty() && Fetch.fetchQueue.isEmpty() && Issue.issueQueue.isEmpty() && Execute.executeQueue.isEmpty();
	}
	private static boolean to_stop() {
		if(!halt) return false;
		return all_units_stopped();
	}
}
