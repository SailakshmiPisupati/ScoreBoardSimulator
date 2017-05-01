package pipelinestages;

import functionunits.FetchUnit;
import functionunits.FunctionalUnit;
import functionunits.IssueUnit;

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
				Instruction instruction = ScoreBoard.instructions.get(startId);
				ArrayList<Register> sourceReg = instruction.getSourceRegisters();
				System.out.println(sourceReg);
				System.out.println(RegisterStatus.destinationRegisters);
				for(int i=0;i<sourceReg.size();i++){
					if(RegisterStatus.destinationRegisters.contains(sourceReg.get(i))){
						System.out.println(sourceReg.get(i));
						System.out.println("RAW hazard");
						OutputStatus.appendTo(startId, 6, 1);
					}
				}
				
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
