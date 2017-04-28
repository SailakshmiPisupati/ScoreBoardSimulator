package functionunits;

import stages.ExecutionStage;
import stages.ReadStage;

public class ReadUnit {
	
	public static boolean isReadBusy=false;

	public static boolean isReadBusy() {
		return isReadBusy;
	}

	public static void setReadBusy(boolean isReadBusy) {
		ReadUnit.isReadBusy = isReadBusy;
	}
	
	public static void execute(int count){
		setReadBusy(true);
		ExecutionStage.executeQueue.add(count);
		ReadStage.readQueue.clear();
		
	}

}
