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
	
	public static void executeFetch(int instructionCount){
		setFetchBusy(true);
		System.out.println("Issue count"+Issue.issuedInstruction);
		Issue.issueQueue.add(Fetch.fetchQueue.get(Issue.issuedInstruction));
	}
}
