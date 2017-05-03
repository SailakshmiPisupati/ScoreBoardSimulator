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
	public static int writeexecuted = -1;
	public static void execute() throws Exception {
		int startId;
		System.out.println("Inside write stage");
		if(!WriteUnit.isWriteBusy){
			if(writeQueue.size()!= 0){
				startId = writeQueue.remove(0);
				OutputStatus.append(startId,5,ScoreBoard.clockCycle);
				WriteUnit.execute(startId);
				ExecuteUnit.setIsexecuteBusy(false);
				writeexecuted = startId;
			}
		}else{
			releaseResources();	//will release resources in a stage when write is not busy. so the FU and Registers are released a stage later.
		}
		
		
	}
	
	
	public static void releaseResources() throws Exception{
		if(WriteUnit.isWriteBusy()){
			
			if(writeexecuted!=-1){
				Instruction instruction = ScoreBoard.instructions.get(writeexecuted);
				FunctionalUnit.releaseUnit(Instruction.getFunctionalUnit(instruction), (writeexecuted));
				String destinationRegister = instruction.getDestinationRegister().toString();
				System.out.println("Attempting to set in write "+destinationRegister);
				RegisterStatus.setDestinationRegisterBusy(destinationRegister,false);
				Issue.issuedInstruction++;
				WriteUnit.setWriteBusy(false);
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