package functionunits;

public class Load {
	private static int noOfUnits = 1;
	private static int executionCycle =2;
	public static boolean isLoadFree = true;
	
	public static boolean isLoadFree() {
		return isLoadFree;
	}
	public static void setLoadFree(boolean isLoadFree) {
		Load.isLoadFree = isLoadFree;
	}
	public static int getNoOfUnits() {
		return noOfUnits;
	}
	public static void setNoOfUnits(int noOfUnits) {
		Load.noOfUnits = noOfUnits;
	}
	
	public static int getExecutionCycle() {
		return executionCycle;
	}
	public static void setExecutionCycle(int executionCycle) {
		Load.executionCycle = executionCycle;
	}
	
	
}
