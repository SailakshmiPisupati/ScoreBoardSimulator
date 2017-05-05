package pipelinestages;

import functionunits.FetchUnit;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.text.AbstractDocument.BranchElement;

import opcodes.Instruction;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;

public class Fetch{

	public static int instructionCount = 0;
	public static int instructionsFetched =0;
	public static HashMap<Integer,Integer> instructionMapping = new HashMap<Integer,Integer>();
	
	public static int getInstructionCount() {
		return instructionCount;
	}
	public static void setInstructionCount(int instructionCount) {
		Fetch.instructionCount = instructionCount;
	}
	public static ArrayList<Integer> fetchQueue = new ArrayList<Integer>();
	public static void execute() {
		System.out.println("fetch "+FetchUnit.isFetchBusy);
		if(!FetchUnit.isFetchBusy && instructionCount != -1){
				int startId = OutputStatus.add();
				instructionMapping.put(instructionsFetched, instructionCount);
				OutputStatus.append(instructionsFetched, 0, instructionCount);
				OutputStatus.append(instructionsFetched, 1, ScoreBoard.clockCycle);
				FetchUnit.executeFetch(instructionsFetched);
				instructionsFetched++;
				instructionCount++;			
		}
	}
}
