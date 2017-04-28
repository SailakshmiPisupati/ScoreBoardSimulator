package functionunits;

import stages.WriteStage;

public class ExecuteUnit {

	public static boolean isexecuteBusy = false;

	public static boolean isIsexecuteBusy() {
		return isexecuteBusy;
	}

	public static void setIsexecuteBusy(boolean isexecuteBusy) {
		ExecuteUnit.isexecuteBusy = isexecuteBusy;
	}
	
	public static void execute(int count){
		setIsexecuteBusy(true);
		WriteStage.writeQueue.add(count);
	}
	
}
