package functionunits;

import opcodes.BEQ;
import opcodes.BNE;
import opcodes.HLT;
import opcodes.Instruction;
import opcodes.J;
import pipelinestages.Fetch;
import pipelinestages.Issue;
import pipelinestages.Read;
import scoreboardstatus.OutputStatus;
import scoreboardstatus.RegisterStatus;
import simulator.ScoreBoard;

public class IssueUnit {
	
	public static boolean isIssueBusy;
	public static String functionalUnit;
	public static boolean unitAvailable =false;
	public static boolean wawHazard = false;
	
	public static boolean isIssueBusy() {
		return isIssueBusy;
	}

	public static void setIssueBusy(boolean isIssueBusy) {
		IssueUnit.isIssueBusy = isIssueBusy;
	}

	public static boolean execute(int startId, int issuedInstruction) throws Exception {
		setIssueBusy(true);
		Instruction instruction = ScoreBoard.instructions.get(startId);
		unitAvailable = checkforFunctionalUnit(instruction,startId,issuedInstruction);
		wawHazard = checkIfWawHazardFound(instruction,startId,issuedInstruction);
		
		if(unitAvailable && !wawHazard){
			FetchUnit.setFetchBusy(false); 					//since the current instruction is being issued, the next set can be fetched.
			//setting function unit as free and also adding the destination register as busy
			FunctionalUnit.assignFunctionalUnit(functionalUnit, startId);
			
			if(instruction instanceof BNE || instruction instanceof BEQ){
				ScoreBoard.halt = true;
				Fetch.setInstructionCount(ScoreBoard.label_map.get(BNE.label));
			}
			else if(instruction instanceof HLT){ // in order to stop the loop
				ScoreBoard.halt = true;
				Fetch.instructionCount = -1;
			}else if(instruction instanceof J){
			}else{
				Read.readQueue.add(startId);
				Read.setReadInstruction(issuedInstruction);
			}
			return true;
		}else{
			return false;
		}
	}

	private static boolean checkIfWawHazardFound(Instruction instruction, int startId, int issuedInstruction) throws Exception {
		if(instruction.getDestinationRegister() != null){
			if(RegisterStatus.checkIfRegisterIsBusy(instruction.getDestinationRegister().toString())){
				System.out.println("***********WAW Hazard detected**************");
				OutputStatus.appendTo(startId, 7, 1);
				//IssueUnit.setIssueBusy(true);				//stall the issue of next instructions since the Functional unit is not free
				return true;
			}else{
				return false;
			}
		}else{
			return false; 				//since no destination register is present,wawHazard is not found (for J,HLT and BNE,BEQ)
		}
	}

	private static boolean checkforFunctionalUnit(Instruction instruction, int startId, int issuedInstruction) {
		functionalUnit = Instruction.getFunctionalUnit(instruction);
		if(FunctionalUnit.checkIfFunctionalUnitIsFree(functionalUnit)){
			return true;
		}else{
			OutputStatus.appendTo(startId, 8, 1);
			//IssueUnit.setIssueBusy(true);				//stall the issue of next instructions since the Functional unit is not free
			return false;
		}
	}
}
