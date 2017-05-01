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
		if(!WriteUnit.isWriteBusy){
			if(writeQueue.size()!= 0){
				int startId = writeQueue.remove(0);
				OutputStatus.appendTo(startId,5,ScoreBoard.clockCycle);
				WriteUnit.execute(startId);
				writeexecuted++;
			}
			
		}
	}
	
	
}
