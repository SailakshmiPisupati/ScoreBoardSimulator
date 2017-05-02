package pipelinestages;

import java.util.ArrayList;

import opcodes.Instruction;
import operands.Register;
import scoreboardstatus.OutputStatus;
import scoreboardstatus.RegisterStatus;
import simulator.ScoreBoard;
import functionunits.FetchUnit;
import functionunits.IssueUnit;
import functionunits.ReadUnit;

// TODO Hazards to handle - RAW (ensure source registers are not being written)

public class Read {
	public static ArrayList<Integer> readQueue = new ArrayList<Integer>();
	public static void execute() throws Exception {
		
		if(!ReadUnit.isReadBusy()){
			if(readQueue.size()!= 0){
				for(int i=0;i<readQueue.size();){
					int startId = readQueue.get(i);
					Instruction instruction = ScoreBoard.instructions.get(startId);
					
					if(areSourcesWritten(instruction)){
						System.out.println("RAW hazard");
						FetchUnit.setFetchBusy(false);
						OutputStatus.appendTo(startId, 6, 1);
						i++;
					}else{
						startId = readQueue.remove(i);
						ReadUnit.execute(startId);
						OutputStatus.appendTo(startId, 3, ScoreBoard.clockCycle);
						break;
					}
				}	
			}		
		}	
	}
	private static boolean areSourcesWritten(Instruction instruction) throws Exception {
		
		ArrayList<Register> sourceReg = instruction.getSourceRegisters();
		if(!sourceReg.isEmpty()){
			for(int i=0;i<sourceReg.size();i++){
				if(RegisterStatus.checkIfRegisterIsBusy(sourceReg.get(i).toString())){
					System.out.println("***********RAW Hazard detected**************");
					return true;
				}
			}	
		}else{
			return false;
		}
		return false;
	}
}
