package stages;

import java.util.ArrayList;

import functionunits.IssueUnit;
import functionunits.ReadUnit;



public class ReadStage {

	public static ArrayList<Integer> readQueue = new ArrayList<Integer>();
	public static void execute() {
		if(!ReadUnit.isReadBusy()){
			
			if(readQueue.size()> 0){
				ReadUnit.execute(readQueue.get(0));
				System.out.println("****Read Stage"+readQueue.get(0));
				IssueStage.issueQueue.clear();
				IssueUnit.setIssueBusy(false);
			}
				
		}
		
		
	}
	
}
