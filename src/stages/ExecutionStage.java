package stages;

import java.util.ArrayList;

import functionunits.ExecuteUnit;
import functionunits.ReadUnit;

public class ExecutionStage {

	public static ArrayList<Integer> executeQueue = new ArrayList<Integer>();
	public static void execute() {
		if(!ExecuteUnit.isexecuteBusy){
			System.out.println("Execute stage");
			if(executeQueue.size()>0){
				System.out.println("***execute queue "+executeQueue.get(0));
				ReadUnit.setReadBusy(false);
			}
		}
		
	}
	
	
}
