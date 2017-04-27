package simulator;

import java.util.HashMap;
import java.util.TreeMap;

import instructions.Instruction;
import parser.*;
import functionunits.*;
import stages.*;

public class ScoreBoard {
	public static TreeMap<Integer, Instruction> instructions = new TreeMap<Integer, Instruction>();
	public static HashMap<String, Integer> label_map = new HashMap<String, Integer>();
	
	/*Passing command line arguments
	 * inst.txt data.txt config.txt result.txt*/
	public static void main(String[] args) {
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
//		int clockCycle =1;
//		boolean finish = false;
//		int instruction =1;
//		String functionalUnit;
//		int fetchforward, issueforward, readforward, writeforward;
//		while(!finish){
//			
//			fetchforward = FetchStage.fetchInstruction(instruction);
//			System.out.println("Cycle "+clockCycle);
//			
//			issueforward = IssueStage.issueInstruction(fetchforward);
//			functionalUnit = Instruction.getFunctionalUnitType(instructions.get(instruction).toString());
//			FunctionalUnit.checkIfFunctionalUnitIsFree(functionalUnit);
//			//FunctionalUnit.assignUnit();
////			System.out.println("Unit is "+functionalUnit);
////			System.out.println("Cycle "+clockCycle);
//			readforward = ReadStage.readInstruction(instructions.get(instruction).toString());
//			
////			System.out.println("Cycle "+clockCycle);
//			writeforward = WriteStage.writeInstruction(readforward);
//			
//			
//			if(instructions.get(instruction).equals("HLT")){// in order to stop the loop
//				finish = true;
//			}
//			clockCycle++;instruction++;
//		}
		
	}
}
