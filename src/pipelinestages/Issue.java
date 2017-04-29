package pipelinestages;

import functionunits.FetchUnit;
import functionunits.IssueUnit;

import java.util.ArrayList;

import opcodes.Instruction;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;

public class Issue {
	public static ArrayList<Integer> issueQueue = new ArrayList<Integer>();
	public static void execute() {
		FetchUnit.setFetchBusy(false);
		if(!IssueUnit.isIssueBusy){
			if(issueQueue.size()!= 0){
//				System.out.println("********Issue Stage - "+issueQueue.remove(0));
				int id = issueQueue.remove(0);
				System.out.println("Issue stage id is :"+ id);
				
				Fetch.fetchQueue.clear();
				IssueUnit.execute(id);
				OutputStatus.append(id, 2, ScoreBoard.clockCycle);
			}			
		}
		
		
	}
	
}
