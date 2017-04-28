package stages;

import java.util.ArrayList;

import simulator.ScoreBoard;
import managers.OutputManager;
import functionunits.ExecuteUnit;
import functionunits.ReadUnit;

public class ExecutionStage {

	public static ArrayList<Integer> executeQueue = new ArrayList<Integer>();
	public static void execute() {
		if(!ExecuteUnit.isexecuteBusy){
			
			if(executeQueue.size()>0){
//				System.out.println("********Execution Stage - "+executeQueue.get(0));
				int id = executeQueue.get(0);
				ExecuteUnit.execute(id);
				ReadStage.readQueue.clear();
				ReadUnit.setReadBusy(false);
				OutputManager.write(id,4,ScoreBoard.clockCycle);
			}
		}
		
	}
	
	
}
