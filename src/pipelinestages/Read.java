package pipelinestages;

import java.util.ArrayList;

import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;
import functionunits.IssueUnit;
import functionunits.ReadUnit;

// TODO Hazards to handle - RAW (ensure source registers are not being written)

public class Read {
	public static ArrayList<Integer> readQueue = new ArrayList<Integer>();
	public static void execute() {
		
		if(!ReadUnit.isReadBusy()){
			if(readQueue.size()!= 0){
				int id = readQueue.remove(0);
				ReadUnit.execute(id);
				OutputStatus.appendTo(id, 3, ScoreBoard.clockCycle);
			}
				
		}
		
		
	}
	
}
