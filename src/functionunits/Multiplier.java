package functionunits;

public class Multiplier {
	private static int noOfUnits;
	private static int executionCycle;
	private static int noOfUnitsUsed;
	
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
}
