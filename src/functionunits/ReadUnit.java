package functionunits;

import opcodes.BEQ;
import opcodes.BNE;
import opcodes.HLT;
import opcodes.Instruction;
import pipelinestages.Execute;
import pipelinestages.Fetch;
import pipelinestages.Issue;
import pipelinestages.Read;
import scoreboardstatus.OutputStatus;
import scoreboardstatus.RegisterStatus;
import simulator.ScoreBoard;

public class ReadUnit {
	
	public static boolean isReadBusy=false;

	public static boolean isReadBusy() {
		return isReadBusy;
	}

	public static void setReadBusy(boolean isReadBusy) {
		ReadUnit.isReadBusy = isReadBusy;
	}
	
	public static void execute(int startId) throws Exception{
		setReadBusy(true);
		int newId = Fetch.instructionMapping.get(startId);
		Instruction instruction = ScoreBoard.instructions.get(newId);
		
		if(instruction instanceof BNE){
			Issue.setBranchCondition(true);
			ScoreBoard.halt = true;
			System.out.println("BNE instruction jumps to "+ScoreBoard.label_map.get(BNE.label));
			Fetch.setInstructionCount(ScoreBoard.label_map.get(BNE.label)); 
			FunctionalUnit.releaseUnit(Instruction.getFunctionalUnit(instruction), (newId));
			Execute.executeQueue.add(startId);
		}else if(instruction instanceof BEQ){
			Issue.setBranchCondition(true);
			ScoreBoard.halt = true;
			FunctionalUnit.releaseUnit(Instruction.getFunctionalUnit(instruction), (newId));
			Fetch.setInstructionCount(ScoreBoard.label_map.get(BEQ.label));
		}else{
			String destinationRegister = instruction.getDestinationRegister().toString();
			RegisterStatus.setDestinationRegisterBusy(destinationRegister,true);
			Execute.executeQueue.add(startId);			
		}
		
		
	}

}
