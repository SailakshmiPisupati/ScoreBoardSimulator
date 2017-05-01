package functionunits;

import java.util.ArrayList;

public class Multiplier {
	private static int noOfUnits;
	private static int executionCycle;
	private static int noOfUnitsUsed;
	public static ArrayList<String> multiplierQueue = new ArrayList<String>();
	public static int multipleCycle[] =new int[10];
	public static int getNoOfUnitsUsed() {
		return noOfUnitsUsed;
	}
	public static void setNoOfUnitsUsed(int noOfUnitsUsed) {
		Multiplier.noOfUnitsUsed = noOfUnitsUsed;
	}
	public static int getNoOfUnits() {
		return noOfUnits;
	}
	public static void setNoOfUnits(int noOfUnits) {
		Multiplier.noOfUnits = noOfUnits;
	}
	public static int getExecutionCycle() {
		return executionCycle;
	}
	public static void setExecutionCycle(int executionCycle) {
		Multiplier.executionCycle = executionCycle;
	}
	public static int getFreeUnits(){
		int freeUnits = getNoOfUnits() - getNoOfUnitsUsed();
		return freeUnits;
	}
	
	public static boolean checkIsFree(){
		if(getFreeUnits()>0){
			return true;
		}
		else{
			return false;
		}
	}
	public static void assignMultiplier() {
		if(multiplierQueue.isEmpty()){
			System.out.println("Adder unit is busy");
			//TODO stall adder instrucitons
		}
		else{
			System.out.println("Assigning multiplier unit");
			String adder = multiplierQueue.remove(0);
		}
		
	}
	public static void initializeMultiplier()throws Exception{
		for(int i=0;i<noOfUnits;i++){
			multiplierQueue.add("Multiplier"+i);
			multipleCycle[i]=0;
		}		
	}
}
