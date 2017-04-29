package functionunits;

import pipelinestages.Execute;
import pipelinestages.Read;

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
		Execute.executeQueue.add(count);
		Read.readQueue.clear();
		
	}

}
