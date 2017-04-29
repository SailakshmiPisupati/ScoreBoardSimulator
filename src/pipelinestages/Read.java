package pipelinestages;

import java.util.ArrayList;

import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;
import functionunits.IssueUnit;
import functionunits.ReadUnit;



public class Read {

	public static ArrayList<Integer> readQueue = new ArrayList<Integer>();
	public static void execute() {
		IssueUnit.setIssueBusy(false);
		if(!ReadUnit.isReadBusy()){
			if(readQueue.size()!= 0){
//				System.out.println("********Read Stage - "+readQueue.get(0));
				int id = readQueue.remove(0);
				ReadUnit.execute(id);
				//Issue.issueQueue.clear();
				OutputStatus.append(id, 3, ScoreBoard.clockCycle);
			}
				
		}
		
		
	}
	
}
