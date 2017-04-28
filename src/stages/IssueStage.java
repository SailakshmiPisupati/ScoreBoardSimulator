package stages;

import functionunits.FetchUnit;
import functionunits.IssueUnit;
import instructions.Instruction;

import java.util.ArrayList;

public class IssueStage {
	public static ArrayList<Integer> issueQueue = new ArrayList<Integer>();
	public static void execute() {
		
		if(issueQueue.size()>0){
			System.out.println("********Issue Stage - "+issueQueue.get(0));
			//FetchStage.fetchQueue.remove(0);
			FetchUnit.setFetchBusy(false);
			IssueUnit.execute(issueQueue.get(0));
		}
		
		
	}
	
}
