package pipelinestages;

import functionunits.FetchUnit;
import functionunits.FunctionalUnit;
import functionunits.IssueUnit;

import java.util.ArrayList;

import opcodes.Instruction;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;

//Hazards to handle - Structural (free FU), WAW (ensure destination registers are not being written)

public class Issue {
	public static boolean branchCondition = false;
	public static int issuedInstruction =0;
	public static boolean unitAvailable =false;
	public static String functionalUnit;
	
	public static boolean isBranchCondition() {
		return branchCondition;
	}
	public static void setBranchCondition(boolean branchCondition) {
		Issue.branchCondition = branchCondition;
	}
	public static ArrayList<Integer> issueQueue = new ArrayList<Integer>();
	public static void execute() {
		int startId =0;
		if(issueQueue.size()!=0){
			startId = issueQueue.get(0);
			functionalUnit = Instruction.getFunctionalUnit(ScoreBoard.instructions.get(startId));
			System.out.println("FU "+functionalUnit);
			
			if(FunctionalUnit.checkIfFunctionalUnitIsFree(functionalUnit)){
				unitAvailable = true;
			}else{
				unitAvailable = false;
				OutputStatus.appendTo(startId, 8, 1);
				IssueUnit.setIssueBusy(true);
			}
		}
		
		if(unitAvailable){
			if(issueQueue.size()!= 0){
				for(int i=0;i<issueQueue.size();i++){
					System.out.println("Issued instructions "+issuedInstruction);
					startId = issueQueue.remove(i);
					System.out.println("Issue stage id is :"+ startId);
					if(branchCondition){
						System.out.println("branch"+branchCondition);
					}
					IssueUnit.execute(startId);
					OutputStatus.append(startId, 2, ScoreBoard.clockCycle);		
				}
					
			}			
		}
	}
}
