package functionunits;

import stages.IssueStage;
import stages.ReadStage;

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
		ReadStage.readQueue.add(count);

	}
	

}
