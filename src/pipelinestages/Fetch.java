package pipelinestages;

import functionunits.FetchUnit;
import functionunits.IssueUnit;
import functionunits.ReadUnit;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.AbstractDocument.BranchElement;

import cache.DCache;
import cache.ICache;
import opcodes.BNE;
import opcodes.HLT;
import opcodes.Instruction;
import scoreboardstatus.MemoryStatus;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;

public class Fetch{

	public static int instructionCount = 0;
	public static int instructionsFetched =0;
	public static boolean done = false;
	public static int getInstructionsFetched() {
		return instructionsFetched;
	}
	public static void setInstructionsFetched(int instructionsFetched) {
		Fetch.instructionsFetched = instructionsFetched;
	}
	public static int lastFetchCycle = 0;
	public static int icachecount =0; 
	public static boolean icacheReady =false;
	public static int nextFetch =0;
	
	public static int getNextFetch() {
		return nextFetch;
	}
	public static void setNextFetch(int nextFetch) {
		Fetch.nextFetch = nextFetch;
	}
	public static boolean isIcacheReady() {
		return icacheReady;
	}
	public static void setIcacheReady(boolean icacheReady) {
		Fetch.icacheReady = icacheReady;
	}
	public static int icacheFetchCycle =0;
	public static int ifetched =0;
	public static int nextInstruction =0;
	public static int getNextInstruction() {
		return nextInstruction;
	}
	public static void setNextInstruction(int nextInstruction) {
		Fetch.nextInstruction = nextInstruction;
	}
	public static HashMap<Integer,Boolean> icachehits =new HashMap<Integer,Boolean>();
	public static HashMap<Integer,Integer> instructionMapping = new HashMap<Integer,Integer>();
	
	public static int getInstructionCount() {
		return instructionCount;
	}
	public static void setInstructionCount(int instructionCount) {
		Fetch.instructionCount = instructionCount;
	}
	public static ArrayList<Integer> fetchQueue = new ArrayList<Integer>();
	public static void execute() {
		if(instructionCount != -1){
				if(ICache.enableIcache && instructionCount != -1){
					boolean hit = ICache.readFromICache(instructionCount);
					if(icacheReady && !FetchUnit.isFetchBusy){
						ifetched++;
						if((ifetched %ICache.getNumberOfBlocks())==0){
							//nextFetch = ScoreBoard.clockCycle +1;
							if(!DCache.DcacheBusy){
								setNextFetch(ScoreBoard.clockCycle +1);
							}
							setIcacheReady(false);
						}
						
						int newInstruction = fetchQueue.remove(0);
						
						if(icachehits.containsKey(newInstruction)){
						}else{
							icachehits.put(newInstruction, true);
						}MemoryStatus.setMemoryReadByCaches(false);
						int startId = OutputStatus.add();
						if(ReadUnit.isBranch && !done){
							done = true;
							nextInstruction = newInstruction +1;
							instructionMapping.put(newInstruction, instructionCount);
							OutputStatus.append(newInstruction, 0, instructionCount);
							OutputStatus.append(newInstruction, 1, ScoreBoard.clockCycle);			
							instructionCount++;	
						}else if(ReadUnit.isBranch && nextInstruction == newInstruction){
							ReadUnit.isBranch = false;
							instructionCount = ReadUnit.branchJumpTo;
							instructionMapping.put(newInstruction, instructionCount);
							OutputStatus.append(newInstruction, 0, instructionCount);
							OutputStatus.append(newInstruction, 1, ScoreBoard.clockCycle);
							FetchUnit.executeFetch(newInstruction);
							
							newInstruction++;
							instructionCount++;	
						}else{
							instructionMapping.put(newInstruction, instructionCount);
							OutputStatus.append(newInstruction, 0, instructionCount);
							OutputStatus.append(newInstruction, 1, ScoreBoard.clockCycle);
							FetchUnit.executeFetch(newInstruction);
							
							newInstruction++;
							instructionCount++;	
						}
						
						
					}else if(!icacheReady && !MemoryStatus.memoryReadByCaches){
						icacheFetchCycle++;
						if(icacheFetchCycle==3){
							//instructionMapping.put(instructionsFetched, instructionCount);
							ICache.ICacheAccessedCount++;
							icacheFetchCycle =0;
							fetchQueue.add(instructionsFetched);
							instructionsFetched++;
							icachecount++;
							if(icachecount % ICache.numberOfBlocks == 0){
								icachehits.put(instructionsFetched, false);
								icacheReady=true;nextFetch =0;
							}
						}
						ICache.fetchNextInstructions(instructionsFetched);
					}
				}else if(!FetchUnit.isFetchBusy && instructionCount!=-1){
					int startId = OutputStatus.add();
					instructionMapping.put(instructionsFetched, instructionCount);
					OutputStatus.append(instructionsFetched, 0, instructionCount);
					OutputStatus.append(instructionsFetched, 1, ScoreBoard.clockCycle);
					FetchUnit.executeFetch(instructionsFetched);
					instructionsFetched++;
					instructionCount++;	
					lastFetchCycle = ScoreBoard.clockCycle;
				}
				
		}
	}
}
