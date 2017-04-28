package stages;

import functionunits.FetchUnit;
import instructions.Instruction;

import java.util.ArrayList;
import managers.OutputManager;
import simulator.ScoreBoard;



public class FetchStage{

	public static int instructionCount = 0;
	public static ArrayList<Integer> fetchQueue = new ArrayList<Integer>();
	public static void execute() {
		
		if(!fetchIsBusy()){
			Instruction instruction = ScoreBoard.instructions.get(instructionCount);
//			System.out.println("*******Fetch Stage "+ instruction.toString());
			fetchQueue.add(instructionCount);
			FetchUnit.executeFetch(instructionCount);
			
			int gid = OutputManager.add();
			OutputManager.write(gid, 0, instructionCount);
			OutputManager.write(gid, 1, ScoreBoard.clockCycle);
			instructionCount++;
		}
		else{
			if(fetchQueue.size()>0){
				fetchQueue.remove(0);
			}
		}
	}
	
	public static boolean fetchIsBusy(){
		return FetchUnit.isFetchBusy;
	}
	
}
