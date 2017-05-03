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
		System.out.println("Inside write stage"+releaseQueue);
		if(!WriteUnit.isWriteBusy){
			if(writeQueue.size()!= 0){
				startId = writeQueue.remove(0);
				releaseQueue.add(startId);
				OutputStatus.append(startId,5,ScoreBoard.clockCycle);
				WriteUnit.execute(startId);
				ExecuteUnit.setIsexecuteBusy(false);
				writeexecuted = startId;
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
					System.out.println("***********Last write executed...releaseing its resources"+toRelease);
					Instruction instruction = ScoreBoard.instructions.get(toRelease);
					FunctionalUnit.releaseUnit(Instruction.getFunctionalUnit(instruction), (toRelease));
					String destinationRegister = instruction.getDestinationRegister().toString();
					System.out.println("Attempting to set in write "+destinationRegister);
					RegisterStatus.setDestinationRegisterBusy(destinationRegister,false);
					Issue.issuedInstruction++;
					WriteUnit.setWriteBusy(false);
				}	
			}
//			if(Execute.isexecute){
//				
//			}else{
//				if(writeexecuted!=0){
//					Instruction instruction = ScoreBoard.instructions.get(writeexecuted-1);
//					FunctionalUnit.releaseUnit(Instruction.getFunctionalUnit(instruction), (writeexecuted-1));
//					String destinationRegister = instruction.getDestinationRegister().toString();
//					System.out.println("Attempting to set in write "+destinationRegister);
//					RegisterStatus.setDestinationRegisterBusy(destinationRegister,false);
//					Issue.issuedInstruction++;
//					WriteUnit.setWriteBusy(false);
//				}
//				
//			}
		}
	}
	
	
}