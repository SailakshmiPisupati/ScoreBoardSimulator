package stages;

import java.util.ArrayList;

import managers.OutputManager;
import simulator.ScoreBoard;
import functionunits.IssueUnit;
import functionunits.ReadUnit;



public class ReadStage {

	public static ArrayList<Integer> readQueue = new ArrayList<Integer>();
	public static void execute() {
		if(!ReadUnit.isReadBusy()){
			
			if(readQueue.size()> 0){
//				System.out.println("********Read Stage - "+readQueue.get(0));
				int id = readQueue.remove(0);
				ReadUnit.execute(id);
				
				IssueStage.issueQueue.clear();
				IssueUnit.setIssueBusy(false);
				
				OutputManager.write(id, 3, ScoreBoard.clockCycle);
			}
				
		}
		
		
	}
	
}
