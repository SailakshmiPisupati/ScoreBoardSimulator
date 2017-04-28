package stages;

import java.util.ArrayList;

import managers.OutputManager;
import simulator.ScoreBoard;
import functionunits.ReadUnit;
import functionunits.WriteUnit;

public class WriteStage {
	int instructionNumber;
	public static ArrayList<Integer> writeQueue = new ArrayList<Integer>();
	public static void execute() {
		if(!WriteUnit.isWriteBusy){
			if(writeQueue.size()>0){
				int id = writeQueue.remove(0);
				WriteUnit.execute(id);
				ReadUnit.setReadBusy(false);
				OutputManager.write(id,5,ScoreBoard.clockCycle);
			}
			
		}
	}
	
	
}
