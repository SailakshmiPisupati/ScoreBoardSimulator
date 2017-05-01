package functionunits;

import java.util.ArrayList;
import java.util.HashMap;

import simulator.ScoreBoard;

public class Adder {
	private static int noOfUnits;
	private static int executionCycle;
	public static HashMap<Integer, Integer> adderQueue = new HashMap<Integer, Integer>();
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
	
//	public static void assignAdder(){
//		int totalAllocated =0;
//		for(int i=0;i<noOfUnits;i++){
//			if(FunctionalUnit.allocatedUnits.contains(adderQueue.get(i))){
//				totalAllocated++;
//			}
//		}
//		if(totalAllocated == noOfUnits){
//			
//		}
//	}
	
//	public static void releaseAdder(String adderValue){
//		adderQueue.add(adderValue);
//		int val= Integer.parseInt(Character.toString(adderValue.charAt(adderValue.length()-1)));
//	}
//	
//	public static void initializeAdders()throws Exception{
//		for(int i=0;i<noOfUnits;i++){
//			adderQueue.put(i, executionCycle);
//		}		
//	}
}
