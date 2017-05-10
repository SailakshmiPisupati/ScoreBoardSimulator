package pipelinestages;

import java.util.ArrayList;
import java.util.HashMap;

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
	public static int noncacheexecCycle = 0;
	public static int executed =0;
	public static int dcacheAccessClock=0;
	public static int executeCyle =0;
	public static boolean canExecute = false;
	public static HashMap<Integer, Integer> instexeccycle = new HashMap<Integer,Integer>();
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
		if(DCache.dCacheEnabled){
			if(!ExecuteUnit.isexecuteBusy){
				if(executeQueue.size()!= 0){
					for(int i=(executeQueue.size()-1);i>=0;i--){
						int startId = executeQueue.get(i);
						int newId = Fetch.instructionMapping.get(startId);
						Instruction instruction = ScoreBoard.instructions.get(newId);
						if(instruction instanceof BEQ || instruction instanceof BNE){
							FetchUnit.setFetchBusy(false);
							executeQueue.remove(i);
						}else if((instruction instanceof LD ||instruction instanceof LW || instruction instanceof SD ||instruction instanceof SW)){
							Issue.issuedInstruction = newId;
							if(!MemoryStatus.memoryReadByCaches && (Fetch.nextFetch == 0 || Fetch.nextFetch >= ScoreBoard.clockCycle) && !canExecute){
								DCache.setDcacheBusy(true);
								boolean valueFound  = ExecuteUnit.accessCacheExecute(newId,startId);
								if(valueFound){
									DCache.setDcacheBusy(false);
									canExecute = true;
								}else{
									dcacheAccessClock++;
									if(dcacheAccessClock == 12){
										DCache.DCachehit++;
										DCache.setDcacheBusy(false);
										canExecute = true;
										executeCyle = ScoreBoard.clockCycle +1;
									}
								}
							}
							
							if(canExecute && executeCyle <= ScoreBoard.clockCycle){
								DCache.DCacheAccessCount++;
								dcacheAccessClock = 0;
								int executionTime;
								if(instruction instanceof LW || instruction instanceof SW){
									executionTime  =1;
									executionCycle++;
								}else{
									executionTime = FunctionalUnit.getLatency(Instruction.getFunctionalUnit(ScoreBoard.instructions.get(newId)));
									if(instexeccycle.containsKey(startId)){
										executionCycle =instexeccycle.remove(startId);
										executionCycle++;
										instexeccycle.put(startId, executionCycle);
										
									}else{
										executionCycle++;
										instexeccycle.put(startId, executionCycle);
										
									}
								}
								
								if(executionTime == executionCycle){
									executionCycle = 0;
									executeQueue.remove(i);
									ExecuteUnit.accessCacheFinished(startId);
									OutputStatus.appendTo(startId,4,ScoreBoard.clockCycle);
									WriteUnit.setWriteBusy(false);
									isexecute = false;
									canExecute = false;
								}else{
									isexecute = true;
								}	
							}
						}else{
							Issue.issuedInstruction = newId;
							int executionTime = FunctionalUnit.getLatency(Instruction.getFunctionalUnit(ScoreBoard.instructions.get(newId)));
							
							if(instexeccycle.containsKey(startId)){
								noncacheexecCycle =instexeccycle.remove(startId);
								noncacheexecCycle++;
								instexeccycle.put(startId, noncacheexecCycle);
								
							}else{
								noncacheexecCycle = 0;
								noncacheexecCycle++;
								instexeccycle.put(startId, noncacheexecCycle);
								
							}
						
							if(executionTime <= noncacheexecCycle){
								noncacheexecCycle = 0;
								executeQueue.remove(i);
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
		}else if(!DCache.dCacheEnabled){
			if(!ExecuteUnit.isexecuteBusy){
				if(executeQueue.size()!= 0){
					for(int i=0;i<executeQueue.size();i++){
						int startId = executeQueue.get(i);
						int newId = Fetch.instructionMapping.get(startId);
						Instruction instruction = ScoreBoard.instructions.get(newId);
						if(instruction instanceof BEQ || instruction instanceof BNE){
							FetchUnit.setFetchBusy(false);
							executeQueue.remove(i);
						}else{
							Issue.issuedInstruction = newId;
							int executionTime;
							if(instruction instanceof LW || instruction instanceof SW){
								executionTime  =1;
								executionCycle++;
							}else{
								executionTime = FunctionalUnit.getLatency(Instruction.getFunctionalUnit(ScoreBoard.instructions.get(newId)));
								executionCycle++;
							}
							
							if(executionTime <= executionCycle){
								executionCycle = 0;
								executeQueue.remove(i);
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
		
	}	
}
