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
		if(!FetchUnit.isFetchBusy && instructionCount != -1){
				if(ICache.enableIcache && instructionCount != -1){
					System.out.println(MemoryStatus.memoryReadByCaches);
					if(!MemoryStatus.memoryReadByCaches){
						boolean hit = ICache.readFromICache(instructionCount);
						if(hit){
							if(icachehits.containsKey(instructionsFetched)){
							}else{
								icachehits.put(instructionsFetched, true);
							}MemoryStatus.setMemoryReadByCaches(false);
							int startId = OutputStatus.add();
							instructionMapping.put(instructionsFetched, instructionCount);
							OutputStatus.append(instructionsFetched, 0, instructionCount);
							OutputStatus.append(instructionsFetched, 1, ScoreBoard.clockCycle);
							FetchUnit.executeFetch(instructionsFetched);
							instructionsFetched++;
							instructionCount++;	
							lastFetchCycle = ScoreBoard.clockCycle;
						}else{
							icachehits.put(instructionsFetched, false);
							if(Read.readQueue.isEmpty()&&Execute.executeQueue.isEmpty()&&Write.writeQueue.isEmpty()&&Issue.issueQueue.isEmpty()){
								ScoreBoard.clockCycle = lastFetchCycle;
								for(int i=0;i<ICache.getBlockSize();i++){
									ICache.fetchNextInstructions(icachecount);
									icachecount++;
								}
								MemoryStatus.setMemoryReadByCaches(false);
							}
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
