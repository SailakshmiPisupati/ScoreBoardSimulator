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
		System.out.println(freeUnits);
		switch(functionalUnit){
			case "Load":
				isFree = Load.getNoOfUnits() == free? true :false;
				break;
			case "Integer":
				isFree = IntegerUnit.getNoOfUnits() == free ? true :false;
				break;
			case "Adder":
				isFree = Adder.getNoOfUnits() == free ? true : false; 
				break;
			case "Divider":
				isFree = Divider.getNoOfUnits() == free ? true :false;
				break;
			case "Multiplier":
				isFree = Multiplier.getNoOfUnits() ==  free ?true :false;
				break;
		}
		
		return isFree;
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

}
