package pipelinestages;

import java.util.ArrayList;
import java.util.HashMap;

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
	public static ArrayList<Integer> releaseQueue = new ArrayList<Integer>();
	public static int writeexecuted = -1;
	public static void execute() throws Exception {
		int startId;
		if(!WriteUnit.isWriteBusy){
			if(writeQueue.size()!= 0){
				startId = writeQueue.remove(0);
				int newId = Fetch.instructionMapping.get(startId);
				releaseQueue.add(newId);
				OutputStatus.append(startId,5,ScoreBoard.clockCycle);
				WriteUnit.execute(newId);
				ExecuteUnit.setIsexecuteBusy(false);
				writeexecuted = newId;
				if(releaseQueue.get(0)<writeexecuted){
					releaseResources();
				}
			}
		}else{
			releaseResources();	//will release resources in a stage when write is not busy. so the FU and Registers are released a stage later.
		}
	}
	
	
	public static void releaseResources() throws Exception{
		if(WriteUnit.isWriteBusy()||releaseQueue.get(0)<writeexecuted){
			if(writeexecuted!=-1){
				for(int i=0;i<releaseQueue.size();i++){
					int toRelease = releaseQueue.remove(i);
					Instruction instruction = ScoreBoard.instructions.get(toRelease);
					FunctionalUnit.releaseUnit(Instruction.getFunctionalUnit(instruction), (toRelease));
					String destinationRegister = instruction.getDestinationRegister().toString();
					RegisterStatus.setDestinationRegisterBusy(destinationRegister,false);
//					Issue.issuedInstruction++;
					int issuedInstruction = Issue.getIssuedInstruction();
					Issue.setIssuedInstruction(issuedInstruction++);
					//WriteUnit.setWriteBusy(false);
				}	
			}
		}
	}
	
	
}