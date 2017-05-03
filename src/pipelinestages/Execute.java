package pipelinestages;

import java.util.ArrayList;

import opcodes.Instruction;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;
import functionunits.ExecuteUnit;
import functionunits.FunctionalUnit;
import functionunits.ReadUnit;
import functionunits.WriteUnit;

public class Execute {
	public static int executionCycle =0;
	public static int getExecutionCycle() {
		return executionCycle;
	}
	public static void setExecutionCycle(int executionCycle) {
		Execute.executionCycle = executionCycle;
	}
	public static boolean isexecute =false;
	public static ArrayList<Integer> executeQueue = new ArrayList<Integer>();
	public static void execute() {
		ReadUnit.setReadBusy(false);
		if(!ExecuteUnit.isexecuteBusy){
			if(executeQueue.size()!= 0){
				
				int id = executeQueue.get(0);
				Issue.issuedInstruction = id;
				int executionTime = FunctionalUnit.getLatency(Instruction.getFunctionalUnit(ScoreBoard.instructions.get(id)));
				executionCycle++;
				System.out.println("Execution time "+executionTime+ "Execution cycle "+executionCycle);
				if(executionTime == executionCycle){
					executionCycle = 0;
					executeQueue.remove(0);
					ExecuteUnit.execute(id);
					OutputStatus.append(id,4,ScoreBoard.clockCycle);
					WriteUnit.setWriteBusy(false);
					isexecute = false;
				}else{
					WriteUnit.setWriteBusy(true);
					isexecute = true;
				}
			}
		}	
	}
}
