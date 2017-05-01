package pipelinestages;

import java.util.ArrayList;

import opcodes.Instruction;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;
import functionunits.IssueUnit;
import functionunits.ReadUnit;

// TODO Hazards to handle - RAW (ensure source registers are not being written)

public class Read {
	public static ArrayList<Integer> readQueue = new ArrayList<Integer>();
	public static void execute() throws Exception {
		
		if(!ReadUnit.isReadBusy()){
			if(readQueue.size()!= 0){
				int id = readQueue.remove(0);
				Instruction instruction = ScoreBoard.instructions.get(id);
				if(instruction.areSourcesBeingWritten()){
					System.out.println("RAW hazard");
				}else{
					ReadUnit.execute(id);
				}
				
				OutputStatus.appendTo(id, 3, ScoreBoard.clockCycle);
				
			}
				
		}
		
		
	}
	
}
