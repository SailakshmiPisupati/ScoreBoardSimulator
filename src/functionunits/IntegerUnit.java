package functionunits;

public class IntegerUnit {
	private static int noOfUnits = 1;
	private static int executionCycle =1;
	public static int getNoOfUnits() {
		return noOfUnits;
	}
	public static void setNoOfUnits(int noOfUnits) {
		IntegerUnit.noOfUnits = noOfUnits;
	}
	public static int getExecutionCycle() {
		return executionCycle;
	}
	public static void setExecutionCycle(int executionCycle) {
		IntegerUnit.executionCycle = executionCycle;
	}
	public static boolean isIntegerFree() {
		return isIntegerFree;
	}
	public static void setIntegerFree(boolean isIntegerFree) {
		IntegerUnit.isIntegerFree = isIntegerFree;
	}
	public static boolean isIntegerFree = true;

}
