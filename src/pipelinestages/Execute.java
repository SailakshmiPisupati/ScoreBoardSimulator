package pipelinestages;

import java.util.ArrayList;

import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;
import functionunits.ExecuteUnit;
import functionunits.ReadUnit;

public class Execute {
	public static int executionCycle =0;
	public static ArrayList<Integer> executeQueue = new ArrayList<Integer>();
	public static void execute() {
		ReadUnit.setReadBusy(false);
		if(!ExecuteUnit.isexecuteBusy){
			if(executeQueue.size()!= 0){
				
				int id = executeQueue.get(0);
				Issue.issuedInstruction = id;
				ExecuteUnit.execute(id);
				executionCycle++;
				OutputStatus.appendTo(id,4,ScoreBoard.clockCycle);
			}
		}	
	}
}
