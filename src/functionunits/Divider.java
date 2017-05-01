package functionunits;

import java.util.ArrayList;

public class Divider {
	private static int noOfUnits;
	private static int executionCycle;
	public static ArrayList<String> dividerQueue = new ArrayList<String>();
	public static int dividerCycle[] =new int[10];
	
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
	public static void assignDivider() {
		if(dividerQueue.isEmpty()){
			System.out.println("Divider unit is busy");
			//TODO stall divider instrucitons
		}
		else{
			System.out.println("Assigning divider unit");
			String divider = dividerQueue.remove(0);
		}
		
		
	}
	
	public static void initializeMultiplier()throws Exception{
		for(int i=0;i<noOfUnits;i++){
			dividerQueue.add("Divider"+i);
			dividerCycle[i]=0;
		}		
	}
	
}
