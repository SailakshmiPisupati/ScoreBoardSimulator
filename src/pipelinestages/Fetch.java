package pipelinestages;

import functionunits.FetchUnit;
import functionunits.IssueUnit;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.AbstractDocument.BranchElement;

import cache.ICache;
import opcodes.Instruction;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;

public class Fetch{

	public static int instructionCount = 0;
	public static int instructionsFetched =0;
	public static int lastFetchCycle = 0;
	public static int icachecount =0; 
	
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
					boolean hit =ICache.readFromICache(instructionCount);
					if(hit){
						int startId = OutputStatus.add();
						instructionMapping.put(instructionsFetched, instructionCount);
						OutputStatus.append(instructionsFetched, 0, instructionCount);
						OutputStatus.append(instructionsFetched, 1, ScoreBoard.clockCycle);
						FetchUnit.executeFetch(instructionsFetched);
						instructionsFetched++;
						instructionCount++;	
						lastFetchCycle = ScoreBoard.clockCycle;
					}else{
						if(Read.readQueue.isEmpty()&&Execute.executeQueue.isEmpty()&&Write.writeQueue.isEmpty()&&Issue.issueQueue.isEmpty()){
							ScoreBoard.clockCycle = lastFetchCycle;
							for(int i=0;i<ICache.getBlockSize();i++){
								ICache.fetchNextInstructions(icachecount);
								icachecount++;
							}
						}
					}
				}
				
		}
	}
}
