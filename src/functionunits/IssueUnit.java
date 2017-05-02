package functionunits;

import opcodes.BEQ;
import opcodes.BNE;
import opcodes.HLT;
import opcodes.Instruction;
import opcodes.J;
import pipelinestages.Fetch;
import pipelinestages.Read;
import scoreboardstatus.RegisterStatus;
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

	public static void execute(int count) throws Exception {
		setIssueBusy(true);
		Instruction instruction = ScoreBoard.instructions.get(count);	
		functionalUnit = Instruction.getFunctionalUnit(ScoreBoard.instructions.get(count));	
		if(FunctionalUnit.checkIfFunctionalUnitIsFree(functionalUnit)){
			FunctionalUnit.assignFunctionalUnit(functionalUnit, count);
			if(instruction instanceof BNE || instruction instanceof BEQ){
				ScoreBoard.halt = true;
				Fetch.setInstructionCount(ScoreBoard.label_map.get(BNE.label));
			}
			else if(instruction instanceof HLT){ // in order to stop the loop
				ScoreBoard.halt = true;
				Fetch.instructionCount = -1;
			}else if(instruction instanceof J){
			}else{
				Read.readQueue.add(count);
			}
//			System.out.println(instruction.getDestinationRegister().toString());
			
			FetchUnit.setFetchBusy(false);
		}else{
			
			FetchUnit.setFetchBusy(true);
		}
		
		
	}

}
