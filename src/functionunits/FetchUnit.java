package functionunits;

import instructions.Instruction;
import simulator.ScoreBoard;
import stages.IssueStage;

public class FetchUnit {
	
	public static boolean isFetchBusy = false;

	public static boolean isFetchBusy() {
		return isFetchBusy;
	}

	public static void setFetchBusy(boolean isFetchBusy) {
		FetchUnit.isFetchBusy = isFetchBusy;
	}
	
	public static void executeFetch(int count){
		setFetchBusy(true);
		IssueStage.issueQueue.add(count);
	}

}
