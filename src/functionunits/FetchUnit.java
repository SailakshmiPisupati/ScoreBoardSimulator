package functionunits;

import opcodes.Instruction;
import pipelinestages.Fetch;
import pipelinestages.Issue;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;

public class FetchUnit {
	
	public static boolean isFetchBusy = false;
	
	
	public static boolean isFetchBusy() {
		return isFetchBusy;
	}

	public static void setFetchBusy(boolean isFetchBusy) {
		FetchUnit.isFetchBusy = isFetchBusy;
	}
	
	public static void executeFetch(int count, int fetched){
		setFetchBusy(true);
		Issue.issueQueue.add(Fetch.fetchQueue.remove(0));
		Issue.setIssuedInstruction(fetched);
	}
}
