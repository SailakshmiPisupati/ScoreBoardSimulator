package pipelinestages;

import java.util.ArrayList;

import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;
import functionunits.ExecuteUnit;
import functionunits.ReadUnit;
import functionunits.WriteUnit;

public class Write {
	int instructionNumber;
	public static ArrayList<Integer> writeQueue = new ArrayList<Integer>();
	public static int writeexecuted =0;
	public static void execute() {
		ExecuteUnit.setIsexecuteBusy(false);
//		if(!WriteUnit.isWriteBusy){
			if(writeQueue.size()!= 0){
				int id = writeQueue.remove(0);
				WriteUnit.execute(id);
				
				OutputStatus.append(id,5,ScoreBoard.clockCycle);
				writeexecuted++;
			}
			
//		}
	}
	
	
}
