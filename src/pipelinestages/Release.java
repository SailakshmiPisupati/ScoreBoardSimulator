package pipelinestages;

import functionunits.ExecuteUnit;
import functionunits.FunctionalUnit;
import functionunits.IssueUnit;
import functionunits.ReadUnit;
import functionunits.WriteUnit;

public class Release {

	public static void execute(){
		if(WriteUnit.isWriteBusy()){
			WriteUnit.setWriteBusy(false);
			if(Execute.executionCycle == FunctionalUnit.getLatency(IssueUnit.functionalUnit)){
				System.out.println("Releasing unit "+IssueUnit.functionalUnit);
				FunctionalUnit.releaseUnit(IssueUnit.functionalUnit, "");
				IssueUnit.setIssueBusy(false);
				ReadUnit.setReadBusy(false);
				ExecuteUnit.setIsexecuteBusy(false);
				Issue.issuedInstruction++;
			}
		}
	}
}
