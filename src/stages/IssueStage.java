package stages;

import functionunits.FetchUnit;
import functionunits.IssueUnit;
import instructions.Instruction;

import java.util.ArrayList;

import simulator.ScoreBoard;
import managers.OutputManager;

public class IssueStage {
	public static ArrayList<Integer> issueQueue = new ArrayList<Integer>();
	public static void execute() {
		
		if(issueQueue.size()>0){
//			System.out.println("********Issue Stage - "+issueQueue.get(0));
			int id = issueQueue.remove(0);
			FetchUnit.setFetchBusy(false);
			IssueUnit.execute(id);
			OutputManager.write(id, 2, ScoreBoard.clockCycle);
		}
		
		
	}
	
}
