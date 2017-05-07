package functionunits;

import java.util.HashMap;

public class FunctionalUnit {

	public static HashMap<String, Integer> allocatedUnits = new HashMap<String, Integer>();
	public static HashMap<String, Integer> freeUnits = new HashMap<String, Integer>();
	public static boolean isloadUnitBusy = false;
	public static boolean isIntegerUnitBusy = false;

	public static boolean checkIfFunctionalUnitIsFree(String functionalUnit) {
		boolean isFree = false;
		int free = freeUnits.get(functionalUnit);
		if(free >0){
			return true;
		}else{
			return false;
		}
	}
	
	public static void assignFunctionalUnit(String functionaUnit, int count){
		int i = freeUnits.get(functionaUnit);
		freeUnits.put(functionaUnit,(i-1));
	}

	public static int getLatency(String functionalUnit) {
		switch(functionalUnit){
		case "Adder":
			return Adder.getExecutionCycle();
		case "Load":
			return Load.getExecutionCycle();
		case "Integer":
			return IntegerUnit.getExecutionCycle();
		case "Divider":
			return Divider.getExecutionCycle();
		case "Multiplier":
			return Multiplier.getExecutionCycle();
		}
		return 0;
	}
	
	public static void releaseUnit(String functionalUnit,int unitValue){
		allocatedUnits.remove(functionalUnit, unitValue);
		int i = freeUnits.get(functionalUnit);
		freeUnits.put(functionalUnit,(i+1));
	}
	public static void initializeFunctionalUnits(){
		FunctionalUnit.freeUnits.put("Adder",Adder.getNoOfUnits());
		FunctionalUnit.freeUnits.put("Multiplier",Multiplier.getNoOfUnits());
		FunctionalUnit.freeUnits.put("Divider",Divider.getNoOfUnits());
		FunctionalUnit.freeUnits.put("Integer",IntegerUnit.getNoOfUnits());
		FunctionalUnit.freeUnits.put("Load",Load.getNoOfUnits());
		FunctionalUnit.freeUnits.put("Other",1);
	}
}
