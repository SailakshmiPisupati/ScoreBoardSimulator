package stages;

import java.util.ArrayList;

import functionunits.WriteUnit;

public class WriteStage {
	int instructionNumber;
	public static ArrayList<Integer> writeQueue = new ArrayList<Integer>();
	public static void execute() {
		if(!WriteUnit.isWriteBusy){
			System.out.println("Write Stage");
		}else{
			System.out.println("Cannot write");
		}	
	}
	
	
}
