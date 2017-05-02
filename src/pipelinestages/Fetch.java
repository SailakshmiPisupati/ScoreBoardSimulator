package pipelinestages;

import functionunits.FetchUnit;

import java.util.ArrayList;

import javax.swing.text.AbstractDocument.BranchElement;

import opcodes.Instruction;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;

public class Fetch{

	public static int instructionCount = 0;
	public static int getInstructionCount() {
		return instructionCount;
	}
	public static void setInstructionCount(int instructionCount) {
		Fetch.instructionCount = instructionCount;
	}
	public static ArrayList<Integer> fetchQueue = new ArrayList<Integer>();
	public static void execute() {
		
		if(!FetchUnit.isFetchBusy && instructionCount != -1){
			fetchQueue.add(instructionCount);
				int startId = OutputStatus.add();
				OutputStatus.append(startId, 0, instructionCount);
				OutputStatus.append(startId, 1, ScoreBoard.clockCycle);
				FetchUnit.executeFetch(instructionCount);
				instructionCount++;			
		}
	}
}
