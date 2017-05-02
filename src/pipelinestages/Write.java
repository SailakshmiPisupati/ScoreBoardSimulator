package pipelinestages;

import java.util.ArrayList;

import opcodes.Instruction;
import scoreboardstatus.OutputStatus;
import scoreboardstatus.RegisterStatus;
import simulator.ScoreBoard;
import functionunits.ExecuteUnit;
import functionunits.FetchUnit;
import functionunits.FunctionalUnit;
import functionunits.IssueUnit;
import functionunits.ReadUnit;
import functionunits.WriteUnit;

public class Write {
	int instructionNumber;
	public static ArrayList<Integer> writeQueue = new ArrayList<Integer>();
	public static int writeexecuted =0;
	public static void execute() throws Exception {
		int startId;
		if(!WriteUnit.isWriteBusy){
			if(writeQueue.size()!= 0){
				startId = writeQueue.remove(0);
				OutputStatus.append(startId,5,ScoreBoard.clockCycle);
				WriteUnit.execute(startId);
				writeexecuted++;
			}
		}else{
			releaseResources();
		}
		
		
	}
	
	
	public static void releaseResources() throws Exception{
		if(WriteUnit.isWriteBusy()){
			if(Execute.isexecute){
				
			}else{
				WriteUnit.setWriteBusy(false);
				Instruction instruction = ScoreBoard.instructions.get(writeexecuted-1);
				FunctionalUnit.releaseUnit(Instruction.getFunctionalUnit(instruction), (writeexecuted-1));
				RegisterStatus.setDestinationRegisterBusy(instruction.getDestinationRegister().toString(),false);
				IssueUnit.setIssueBusy(false);
				ReadUnit.setReadBusy(false);
				ExecuteUnit.setIsexecuteBusy(false);
				FetchUnit.setFetchBusy(false);
				Issue.issuedInstruction++;
				Execute.setExecutionCycle(0);
			}
		}
	}
	
	
}
