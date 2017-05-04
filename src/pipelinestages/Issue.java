package pipelinestages;

import functionunits.FetchUnit;
import functionunits.FunctionalUnit;
import functionunits.IssueUnit;
import functionunits.ReadUnit;

import java.util.ArrayList;

import opcodes.Instruction;
import operands.Register;
import scoreboardstatus.OutputStatus;
import scoreboardstatus.RegisterStatus;
import simulator.ScoreBoard;

//Hazards to handle - Structural (free FU), WAW (ensure destination registers are not being written)

public class Issue {
	public static boolean branchCondition = false;
	public static int issuedInstruction =0;
	public static int issued =0;

	public static int getIssued() {
		return issued;
	}
	public static void setIssued(int issued) {
		Issue.issued = issued;
	}
	public static int getIssuedInstruction() {
		return issuedInstruction;
	}
	public static void setIssuedInstruction(int issuedInstruction) {
		Issue.issuedInstruction = issuedInstruction;
	}
	public static boolean isBranchCondition() {
		return branchCondition;
	}
	public static void setBranchCondition(boolean branchCondition) {
		Issue.branchCondition = branchCondition;
	}
	public static ArrayList<Integer> issueQueue = new ArrayList<Integer>();
	
	public static void execute() throws Exception {
		int startId =0;
		
		if(issueQueue.size()!= 0){
			for(int i=0;i<issueQueue.size();i++){
				startId = issueQueue.get(i);
				int newId = Fetch.instructionMapping.get(startId);
				System.out.println("newid in issue"+newId);
				boolean isissued = IssueUnit.execute(startId, newId);
				if(isissued){
					issueQueue.remove(i);
					OutputStatus.append(startId, 2, ScoreBoard.clockCycle);
					break;
				}
				
			}
		}
	}
}