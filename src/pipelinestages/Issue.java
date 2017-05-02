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
	public static boolean unitAvailable =false;
	public static String functionalUnit;
	public static boolean wawHazard = false;

	public static boolean isBranchCondition() {
		return branchCondition;
	}
	public static void setBranchCondition(boolean branchCondition) {
		Issue.branchCondition = branchCondition;
	}
	public static ArrayList<Integer> issueQueue = new ArrayList<Integer>();
	public static void execute() throws Exception {
		int startId =0;
		if(issueQueue.size()!=0){
			startId = issueQueue.get(0);
			Instruction instruction = ScoreBoard.instructions.get(startId);
			functionalUnit = Instruction.getFunctionalUnit(instruction);
			if(FunctionalUnit.checkIfFunctionalUnitIsFree(functionalUnit)){
				unitAvailable = true;
			}else{
				unitAvailable = false;
				OutputStatus.appendTo(startId, 8, 1);
				IssueUnit.setIssueBusy(true);
			}
			
			if(RegisterStatus.checkIfRegisterIsBusy(instruction.getDestinationRegister().toString())){
				System.out.println("***********WAW Hazard detected**************");
				//FetchUnit.setFetchBusy(true);
				OutputStatus.appendTo(startId, 7, 1);
				wawHazard = true;
			}else{
//				FetchUnit.setFetchBusy(true);
				wawHazard = false;
			}
		}
		 
		if(unitAvailable && !wawHazard){
			if(issueQueue.size()!= 0){
				Instruction instruction = ScoreBoard.instructions.get(startId);
			
				for(int i=0;i<issueQueue.size();i++){
					startId = issueQueue.remove(i);
					IssueUnit.execute(startId);
					OutputStatus.append(startId, 2, ScoreBoard.clockCycle);		
				}
			}
			}			
		}
	}
