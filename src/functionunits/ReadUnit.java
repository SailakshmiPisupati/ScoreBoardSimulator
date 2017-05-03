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
	
	public static void execute(int count) throws Exception{
		setReadBusy(true);
		Instruction instruction = ScoreBoard.instructions.get(count);
		String destinationRegister = instruction.getDestinationRegister().toString();
		RegisterStatus.setDestinationRegisterBusy(destinationRegister,true);
		if(instruction instanceof BNE){
			Issue.setBranchCondition(true);
			Fetch.setInstructionCount(ScoreBoard.label_map.get(BNE.label)); 
			int instructioncount = Fetch.getInstructionCount();
			Fetch.fetchQueue.add(instructioncount);
		}else if(instruction instanceof BEQ){
			Issue.setBranchCondition(true);
			System.out.println(BEQ.label);
			Fetch.setInstructionCount(ScoreBoard.label_map.get(BEQ.label));
			Fetch.fetchQueue.add(ScoreBoard.label_map.get(BEQ.label));
		}else{
			Execute.executeQueue.add(count);			
		}
		
		
	}

}
