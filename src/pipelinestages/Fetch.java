package pipelinestages;

import functionunits.FetchUnit;

import java.util.ArrayList;

import opcodes.Instruction;
import scoreboardstatus.OutputStatus;
import simulator.ScoreBoard;

public class Fetch{

	public static int instructionCount = 0;
	public static ArrayList<Integer> fetchQueue = new ArrayList<Integer>();
	public static void execute() {
		
		if(!FetchUnit.isFetchBusy && instructionCount != -1){			
			Instruction instruction = ScoreBoard.instructions.get(instructionCount);
			fetchQueue.add(instructionCount);
			FetchUnit.executeFetch();
			
			int id = OutputStatus.add();
			OutputStatus.append(id, 0, instructionCount);
			OutputStatus.append(id, 1, ScoreBoard.clockCycle);
			instructionCount++;
		}
	}
}
