package functionunits;

import pipelinestages.Issue;
import pipelinestages.Read;

public class IssueUnit {
	
	public static boolean isIssueBusy;

	public static boolean isIssueBusy() {
		return isIssueBusy;
	}

	public static void setIssueBusy(boolean isIssueBusy) {
		IssueUnit.isIssueBusy = isIssueBusy;
	}

	public static void execute(int count) {
		setIssueBusy(true);
		Read.readQueue.add(count);

	}
	

}
