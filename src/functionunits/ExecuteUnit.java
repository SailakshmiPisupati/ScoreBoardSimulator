package functionunits;

import opcodes.Instruction;
import pipelinestages.Execute;
import pipelinestages.Write;
import simulator.ScoreBoard;

public class ExecuteUnit {

	public static boolean isexecuteBusy = false;

	public static boolean isIsexecuteBusy() {
		return isexecuteBusy;
	}

	public static void setIsexecuteBusy(boolean isexecuteBusy) {
		ExecuteUnit.isexecuteBusy = isexecuteBusy;
	}
	
	public static void execute(int count,int startId) throws Exception{
		//setIsexecuteBusy(true);
		Instruction instruction = ScoreBoard.instructions.get(count);
		instruction.execute();
		instruction.writeToRegister();
		Write.writeQueue.add(startId);
	}
	
	public static void accessCacheExecute(int count,int startId)throws Exception{
		Instruction instruction = ScoreBoard.instructions.get(count);
		instruction.execute();
		instruction.writeToRegister();
	}
	
	public static void accessCacheFinished(int startId){
		Write.writeQueue.add(startId);
	}
	
}
