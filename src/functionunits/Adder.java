package functionunits;

public class Adder {
	private static int noOfUnits;
	private static int executionCycle;

	public static int getNoOfUnits() {
		return noOfUnits;
	}
	public static void setNoOfUnits(int noOfUnits) {
		Adder.noOfUnits = noOfUnits;
	}
	
	public static int getExecutionCycle() {
		return executionCycle;
	}
	public static void setExecutionCycle(int executionCycle) {
		Adder.executionCycle = executionCycle;
	}

}
