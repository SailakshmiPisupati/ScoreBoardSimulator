package pipelinestages;

import functionunits.FetchUnit;
import functionunits.IssueUnit;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.AbstractDocument.BranchElement;

import cache.ICache;
import opcodes.Instruction;
import scoreboardstatus.MemoryStatus;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;

public class Fetch{

	public static int instructionCount = 0;
	public static int instructionsFetched =0;
	public static int lastFetchCycle = 0;
	public static int icachecount =0; 
	public static boolean icacheReady =false;
	public static boolean isIcacheReady() {
		return icacheReady;
	}
	public static void setIcacheReady(boolean icacheReady) {
		Fetch.icacheReady = icacheReady;
	}
	public static int icacheFetchCycle =0;
	public static int ifetched =0;
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
					System.out.println(MemoryStatus.memoryReadByCaches);
					if(!MemoryStatus.memoryReadByCaches){
						boolean hit = ICache.readFromICache(instructionCount);
						if(icacheReady && !FetchUnit.isFetchBusy ){
							ifetched++;
							if((ifetched %ICache.getNumberOfBlocks())==0){
								setIcacheReady(false);
							}
							int newInstruction = fetchQueue.remove(0);
							if(icachehits.containsKey(newInstruction)){
							}else{
								icachehits.put(newInstruction, true);
							}MemoryStatus.setMemoryReadByCaches(false);
							int startId = OutputStatus.add();
							instructionMapping.put(newInstruction, instructionCount);
							OutputStatus.append(newInstruction, 0, instructionCount);
							OutputStatus.append(newInstruction, 1, ScoreBoard.clockCycle);
							FetchUnit.executeFetch(newInstruction);
							newInstruction++;
							instructionCount++;	
							lastFetchCycle = ScoreBoard.clockCycle;
						}else if(!icacheReady){
							
							icachehits.put(instructionsFetched, false);
							icacheFetchCycle++;
							
							if(icacheFetchCycle==3){
								icacheFetchCycle =0;
								fetchQueue.add(instructionsFetched);
								instructionsFetched++;
								icachecount++;
								if(icachecount % ICache.numberOfBlocks == 0){
									icacheReady=true;
								}
							}
							
							ICache.fetchNextInstructions(instructionsFetched);
						}
					}
				}else if(instructionCount!=-1){
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
