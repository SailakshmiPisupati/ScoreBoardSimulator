package functionunits;

import pipelinestages.Execute;
import pipelinestages.Write;

public class ExecuteUnit {

	public static boolean isexecuteBusy = false;

	public static boolean isIsexecuteBusy() {
		return isexecuteBusy;
	}

	public static void setIsexecuteBusy(boolean isexecuteBusy) {
		ExecuteUnit.isexecuteBusy = isexecuteBusy;
	}
	
	public static void execute(int count){
		//setIsexecuteBusy(true);
		Write.writeQueue.add(count);
	}
	
}
