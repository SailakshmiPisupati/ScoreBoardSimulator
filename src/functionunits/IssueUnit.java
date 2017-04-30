package functionunits;

import opcodes.HLT;
import opcodes.Instruction;
import opcodes.J;
import pipelinestages.Fetch;
import pipelinestages.Issue;
import pipelinestages.Read;
import simulator.ScoreBoard;

public class IssueUnit {
	
	public static boolean isIssueBusy;

	public static boolean isIssueBusy() {
		return isIssueBusy;
	}

	public static void setIssueBusy(boolean isIssueBusy) {
		IssueUnit.isIssueBusy = isIssueBusy;
	}

	public static void execute(int count) {
		setIssueBusy(true);
		
		Instruction inst = ScoreBoard.instructions.get(count);
		System.out.println("Instruction in issue is :"+ inst.toString()+" opcode is "+inst.getOpcode());
		
		if(inst instanceof HLT){ // in order to stop the loop
			ScoreBoard.halt = true;
			Fetch.instructionCount = -1;
		}else if(inst instanceof J){
		}else{
			Read.readQueue.add(count);
		}

	}
	

}
