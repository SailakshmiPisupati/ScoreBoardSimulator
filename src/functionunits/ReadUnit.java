package functionunits;

import cache.DCache;
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
	public static boolean isBranch = false;
	public static int branchJumpTo = 0;
	
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
			if(((BNE) instruction).checkBranchCondition()){
				//ScoreBoard.halt = true;

				if(DCache.dCacheEnabled){
					isBranch = true;
					branchJumpTo = ScoreBoard.labelLocation.get(BNE.label);
					Fetch.setNextInstruction(ScoreBoard.labelLocation.get(BNE.label));
				}else{
					Fetch.setInstructionCount(ScoreBoard.labelLocation.get(BNE.label));
				}
				FunctionalUnit.releaseUnit(Instruction.getFunctionalUnit(instruction), (newId));
				Execute.executeQueue.add(startId);
				FetchUnit.setFetchBusy(true);
				IssueUnit.setIssueBusy(true);
				Issue.issueQueue.clear();
			}else{
				FetchUnit.setFetchBusy(false);
				IssueUnit.setIssueBusy(false);
			}
		}else if(instruction instanceof BEQ){
			if(((BEQ) instruction).checkBranchCondition()){
				//ScoreBoard.halt = true;
				if(DCache.dCacheEnabled){
					isBranch = true;
					branchJumpTo = ScoreBoard.labelLocation.get(BEQ.label);
					Fetch.setNextInstruction(ScoreBoard.labelLocation.get(BEQ.label));
				}else{
					Fetch.setInstructionCount(ScoreBoard.labelLocation.get(BEQ.label));
				}
				FunctionalUnit.releaseUnit(Instruction.getFunctionalUnit(instruction), (newId));
				
				Execute.executeQueue.add(startId);
				Issue.issueQueue.clear();
				FetchUnit.setFetchBusy(true);
				IssueUnit.setIssueBusy(true);
			}else{
				FetchUnit.setFetchBusy(false);
				IssueUnit.setIssueBusy(false);
			}
			
		}else if(instruction instanceof HLT){
			Fetch.instructionCount = -1;
		}else{
			String destinationRegister = instruction.getDestinationRegister().toString();
			RegisterStatus.setDestinationRegisterBusy(destinationRegister,true);
			Execute.executeQueue.add(startId);	
			IssueUnit.setIssueBusy(false);
		}
		
		
	}

}
