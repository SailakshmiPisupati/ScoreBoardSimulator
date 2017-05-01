package functionunits;

import java.util.ArrayList;

import simulator.ScoreBoard;

public class Adder {
	private static int noOfUnits;
	private static int executionCycle;
	public static ArrayList<String> adderQueue = new ArrayList<String>();
	public static int adderCycle[] =new int[10];
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
	
	public static void assignAdder(){
		if(adderQueue.isEmpty()){
			System.out.println("Adder unit is busy");
			//TODO stall adder instrucitons
		}
		else{
			System.out.println("Assigning adder unit");
			String adder = adderQueue.remove(0);
		}
	}
	
	public static void releaseAdder(String adderValue){
		adderQueue.add(adderValue);
		int val= Integer.parseInt(Character.toString(adderValue.charAt(adderValue.length()-1)));
		adderCycle[val]=ScoreBoard.clockCycle;
	}
	
	public static void initializeAdders()throws Exception{
		for(int i=0;i<noOfUnits;i++){
			adderQueue.add("Adder"+i);
			adderCycle[i]=0;
		}		
	}
}
