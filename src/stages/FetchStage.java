package stages;

import functionunits.FetchUnit;
import instructions.Instruction;

import java.util.ArrayList;

import managers.OutputManager;
import simulator.ScoreBoard;



public class FetchStage{

	public static int instructionCount = 1;
	public static ArrayList<Integer> fetchQueue = new ArrayList<Integer>();
	public static void execute() {
		
		if(!fetchIsBusy()){
			Instruction instruction = ScoreBoard.instructions.get(instructionCount);
			System.out.println("*******Fetch Stage "+ instruction.toString());
			FetchUnit.executeFetch(instructionCount);
			instructionCount++;
//			int gid = OutputManager.add();
//			OutputManager.write(gid, 0, instructionCount);
		}
		else{
			if(fetchQueue.size()>0){
				fetchQueue.remove(instructionCount);
			}
		}
	}
	
	public static boolean fetchIsBusy(){
		return FetchUnit.isFetchBusy;
	}
	
}
