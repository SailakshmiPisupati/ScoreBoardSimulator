package functionunits;

import opcodes.BEQ;
import opcodes.BNE;
import opcodes.HLT;
import opcodes.Instruction;
import pipelinestages.Execute;
import pipelinestages.Fetch;
import pipelinestages.Issue;
import pipelinestages.Read;
import simulator.ScoreBoard;

public class ReadUnit {
	
	public static boolean isReadBusy=false;

	public static boolean isReadBusy() {
		return isReadBusy;
	}

	public static void setReadBusy(boolean isReadBusy) {
		ReadUnit.isReadBusy = isReadBusy;
	}
	
	public static void execute(int count){
		setReadBusy(true);
		Instruction instruction = ScoreBoard.instructions.get(count);
		if(instruction instanceof BNE){
			Issue.setBranchCondition(true);
			System.out.println(BNE.label);
			System.out.println("new branch count"+ScoreBoard.label_map.get(BNE.label));
			Fetch.setInstructionCount(ScoreBoard.label_map.get(BNE.label)); 
			int instructioncount = Fetch.getInstructionCount();
			System.out.println("INstruction is"+instructioncount);
			
//			Fetch.fetchQueue.add(ScoreBoard.label_map.get(BNE.label));
			Fetch.fetchQueue.add(instructioncount);
			
//			TODO
//			if condition passes{
//				change fetch stages's count
//			}
		}else if(instruction instanceof BEQ){
			Issue.setBranchCondition(true);
			System.out.println(BEQ.label);
			System.out.println("new branch count"+ScoreBoard.label_map.get(BEQ.label));
			Fetch.setInstructionCount(ScoreBoard.label_map.get(BEQ.label));
			
			Fetch.fetchQueue.add(ScoreBoard.label_map.get(BEQ.label));
//			TODO
//			if condition passes{
//				change fetch stages's count
//			}
		}else{
			Execute.executeQueue.add(count);			
		}
		Read.readQueue.clear();
		
	}

}
