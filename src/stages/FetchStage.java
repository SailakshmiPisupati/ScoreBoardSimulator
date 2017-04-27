package stages;

import instructionopcodes.Instruction;

public class FetchStage extends Thread{
	int instructionNumber;
	
	public static int fetchInstruction(int instructionNumber){
		
//		System.out.println("Instruction number "+instructionNumber+" at Fetch stage");
		
			
		
		
		//check if the unit is free - return true
		//if free send the instruction to next stage - return false
		//else halt.
		
		return instructionNumber++;
	}
	
	public void run(){
		System.out.println("Instruction number in fetch");
	}
}
