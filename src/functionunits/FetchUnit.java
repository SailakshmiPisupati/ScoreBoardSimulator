package functionunits;

import opcodes.Instruction;
import pipelinestages.Fetch;
import pipelinestages.Issue;
import simulator.ScoreBoard;

public class FetchUnit {
	
	public static boolean isFetchBusy = false;

	public static boolean isFetchBusy() {
		return isFetchBusy;
	}

	public static void setFetchBusy(boolean isFetchBusy) {
		FetchUnit.isFetchBusy = isFetchBusy;
	}
	
	public static void executeFetch(){
		setFetchBusy(true);
		int fetchQueueval = Fetch.fetchQueue.get(0);
		System.out.println("Fetch q "+fetchQueueval);
		Issue.issueQueue.add(fetchQueueval);
	}
}
