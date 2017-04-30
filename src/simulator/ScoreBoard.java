package simulator;

import java.util.HashMap;
import java.util.TreeMap;

import opcodes.Instruction;
import parser.*;
import pipelinestages.*;
import scoreboardstatus.OutputStatus;
import functionunits.*;

public class ScoreBoard {
	private static final int MAX_CYCLE_COUNT = 30;
	public static TreeMap<Integer, Instruction> instructions = new TreeMap<Integer, Instruction>();
	public static HashMap<String, Integer> label_map = new HashMap<String, Integer>();
	public static int clockCycle =1 ;
	public static boolean halt = false;

	/*Passing command line arguments
	 * inst.txt data.txt config.txt result.txt*/
	public static void main(String[] args) throws Exception {
		System.out.println("Simulator starts");
		
		InstructionParser.readFile(args[0]);
		DataParser.readFile(args[1]);
		ConfigParser.readFile(args[2]);
		
		runinstructions();
		//Register.initialise();
//		String outputFile = args[3];
		
	}
	public static void runinstructions() {
		//fetch the instructions and get the instruction type of each instruction		
		int instruction =1;
	
		
		while(clockCycle < MAX_CYCLE_COUNT){

//			System.out.println(instructions.get(instruction));
			Write.execute();
			Execute.execute();
			Read.execute();
			Issue.execute();
			Fetch.execute();
			OutputStatus.printResults();
			//System.out.println("Cycle "+clockCycle);
		
			if(to_stop()) break;				
						
			clockCycle++;
			instruction++;
		}
		
	}
	
	private static boolean all_units_stopped() {
		// TODO Auto-generated method stub
		return Write.writeQueue.isEmpty()&& Read.readQueue.isEmpty() && Fetch.fetchQueue.isEmpty() && Issue.issueQueue.isEmpty() && Execute.executeQueue.isEmpty();
	}
	private static boolean to_stop() {
		if(!halt) return false;
		
		return all_units_stopped();
	}
}
