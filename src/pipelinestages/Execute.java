package pipelinestages;

import java.util.ArrayList;

import cache.DCache;
import opcodes.BEQ;
import opcodes.BNE;
import opcodes.Instruction;
import opcodes.LD;
import opcodes.LW;
import opcodes.SD;
import opcodes.SW;
import scoreboardstatus.MemoryStatus;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;
import functionunits.ExecuteUnit;
import functionunits.FetchUnit;
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
	public static void execute() throws Exception {
		ReadUnit.setReadBusy(false);
		if(!ExecuteUnit.isexecuteBusy){
			if(executeQueue.size()!= 0){
				int startId = executeQueue.get(0);
				int newId = Fetch.instructionMapping.get(startId);
				Instruction instruction = ScoreBoard.instructions.get(newId);
				if(instruction instanceof BEQ || instruction instanceof BNE){
					FetchUnit.setFetchBusy(false);
					executeQueue.remove(0);
				}else if((instruction instanceof LD ||instruction instanceof LW || instruction instanceof SD ||instruction instanceof SW)&&DCache.dCacheEnabled){
					Issue.issuedInstruction = newId;
					int executionTime = FunctionalUnit.getLatency(Instruction.getFunctionalUnit(ScoreBoard.instructions.get(newId)));
					executionCycle++;
					if(executionTime == executionCycle){
						if(MemoryStatus.memoryReadByCaches){
							System.out.println("memory busy");
						}else{
							executionCycle = 0;
							executeQueue.remove(0);
							ExecuteUnit.execute(newId,startId);
							OutputStatus.append(startId,4,ScoreBoard.clockCycle);
							WriteUnit.setWriteBusy(false);
							isexecute = false;
						}
					}else{
						isexecute = true;
					}
				}else{
					Issue.issuedInstruction = newId;
					int executionTime = FunctionalUnit.getLatency(Instruction.getFunctionalUnit(ScoreBoard.instructions.get(newId)));
					executionCycle++;
					if(executionTime == executionCycle){
						executionCycle = 0;
						executeQueue.remove(0);
						ExecuteUnit.execute(newId,startId);
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
}
