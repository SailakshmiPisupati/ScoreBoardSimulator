package pipelinestages;

import functionunits.FetchUnit;
import functionunits.IssueUnit;

import java.util.ArrayList;

import opcodes.Instruction;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;

//Hazards to handle - Structural (free FU), WAW (ensure destination registers are not being written)

public class Issue {
	public static boolean branchCondition = false;
	public static int issuedInstruction =0;
	public static boolean isBranchCondition() {
		return branchCondition;
	}
	public static void setBranchCondition(boolean branchCondition) {
		Issue.branchCondition = branchCondition;
	}
	public static ArrayList<Integer> issueQueue = new ArrayList<Integer>();
	public static void execute() {
		FetchUnit.setFetchBusy(false);
		if(!IssueUnit.isIssueBusy){
			if(issueQueue.size()!= 0){
//				for(int i=issuedInstruction;i<issueQueue.size();i++){
					int startId = issueQueue.remove(issuedInstruction);
					System.out.println("Issue stage id is :"+ startId);
					if(branchCondition){
						System.out.println("branch"+branchCondition);
					}
						
					IssueUnit.execute(startId);
					OutputStatus.append(startId, 2, ScoreBoard.clockCycle);
					
				//}
				issuedInstruction++;
				System.out.println("Issued instructions "+issuedInstruction);
			}			
		}
		
		
	}
	
}
