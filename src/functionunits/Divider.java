package functionunits;

import java.util.ArrayList;

public class Divider {
	private static int noOfUnits;
	private static int executionCycle;
	public static ArrayList<Integer> dividerQueue = new ArrayList<Integer>();
	
	
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
