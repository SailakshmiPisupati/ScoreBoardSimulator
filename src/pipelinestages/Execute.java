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
	public static int executed =0;
	public static int getExecuted() {
		return executed;
	}
	public static void setExecuted(int executed) {
		Execute.executed = executed;
	}
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
				
				int startId = executeQueue.get(0);
				int newId = Fetch.instructionMapping.get(startId);
				Issue.issuedInstruction = newId;
				int executionTime = FunctionalUnit.getLatency(Instruction.getFunctionalUnit(ScoreBoard.instructions.get(newId)));
				executionCycle++;
				System.out.println("Execution time "+executionTime+ "Execution cycle "+executionCycle);
				if(executionTime == executionCycle){
					executionCycle = 0;
					executeQueue.remove(0);
					ExecuteUnit.execute(startId);
					OutputStatus.append(startId,4,ScoreBoard.clockCycle);
					WriteUnit.setWriteBusy(false);
					isexecute = false;
				}else{
					isexecute = true;
				}
			}
		}	
	}
}
