package pipelinestages;

import java.util.ArrayList;

import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;
import functionunits.ExecuteUnit;
import functionunits.ReadUnit;

public class Execute {

	public static ArrayList<Integer> executeQueue = new ArrayList<Integer>();
	public static void execute() {
		ReadUnit.setReadBusy(false);
		if(!ExecuteUnit.isexecuteBusy){
			if(executeQueue.size()!= 0){
//				System.out.println("********Execution Stage - "+executeQueue.get(0));
				int id = executeQueue.get(0);
				ExecuteUnit.execute(id);
				//Read.readQueue.clear();
				
				OutputStatus.append(id,4,ScoreBoard.clockCycle);
			}
		}	
	}
}
