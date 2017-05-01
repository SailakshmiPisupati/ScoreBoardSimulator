package functionunits;

import java.util.function.Function;

import opcodes.BEQ;
import opcodes.BNE;
import opcodes.HLT;
import opcodes.Instruction;
import opcodes.J;
import pipelinestages.Fetch;
import pipelinestages.Issue;
import pipelinestages.Read;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;

public class IssueUnit {
	
	public static boolean isIssueBusy;
	public static String functionalUnit;
	public static boolean isIssueBusy() {
		return isIssueBusy;
	}

	public static void setIssueBusy(boolean isIssueBusy) {
		IssueUnit.isIssueBusy = isIssueBusy;
	}

	public static void execute(int count) {
		setIssueBusy(true);
		functionalUnit = Instruction.getFunctionalUnit(ScoreBoard.instructions.get(count));
		System.out.println("FU "+functionalUnit);
		
		//TODO check if the functional unit is free
		if(FunctionalUnit.checkIfFunctionalUnitIsFree(functionalUnit)){
			FunctionalUnit.assignFunctionalUnit(functionalUnit);
			Instruction inst = ScoreBoard.instructions.get(count);
			System.out.println("Instruction in issue is :"+ inst.toString()+" opcode is "+inst.getOpcode());
			if(inst instanceof BNE || inst instanceof BEQ){
				ScoreBoard.halt = true;
				Fetch.fetchQueue.clear();
				Fetch.setInstructionCount(ScoreBoard.label_map.get(BNE.label));
			}
			else if(inst instanceof HLT){ // in order to stop the loop
				ScoreBoard.halt = true;
				Fetch.instructionCount = -1;
			}else if(inst instanceof J){
			}else{
				Read.readQueue.add(count);
			}

		}else{
			//TODO make the structural hazrd
		}
		
		
	}
	

}
