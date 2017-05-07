package functionunits;

public class Divider {
	private static int noOfUnits;
	private static int executionCycle;
	
	public static int getNoOfUnits() {
		return noOfUnits;
	}
	public static void setNoOfUnits(int noOfUnits) {
		Divider.noOfUnits = noOfUnits;
	}
	public static int getExecutionCycle() {
		return executionCycle;
	}
	public static void setExecutionCycle(int executionCycle) {
		Divider.executionCycle = executionCycle;
	}
	
}
