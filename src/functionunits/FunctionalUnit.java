package functionunits;

import java.util.ArrayList;

public class FunctionalUnit {

	public static ArrayList<Adder> adderUnit = new ArrayList<Adder>();
	public static ArrayList<Divider> dividerUnit = new ArrayList<Divider>();
	public static ArrayList<Multiplier> multiplierUnit = new ArrayList<Multiplier>();
	public static boolean isloadUnitBusy = false;
	public static boolean isIntegerUnitBusy = false;

	public static boolean checkIfFunctionalUnitIsFree(String functionalUnit) {
		boolean isFree = false;
		switch(functionalUnit){
			case "Load":
				isFree = Load.isLoadFree();
				break;
			case "Integer":
				isFree = IntegerUnit.isIntegerFree();
				break;
			case "Adder":
				isFree = (adderUnit.size() == Adder.getNoOfUnits())?true : false; 
				break;
			case "Divider":
				isFree = (dividerUnit.size() == Divider.getNoOfUnits())?true : false;
				break;
			case "Multiplier":
				isFree = (multiplierUnit.size() == Multiplier.getNoOfUnits())?true : false;
				break;
		}
		
		return isFree;
	}
	
	public static void assignFunctionalUnit(String functionaUnit){
		switch(functionaUnit){
		case "Adder":
			Adder.assignAdder();
		case "Load":
			Load.setLoadFree(false);
		case "Integer":
			IntegerUnit.setIntegerFree(false);
//		case "Divider":
//			Divider.assignDivider();
//		case "Multiplier":
//			Multiplier.assignMultiplier();
		}
	}

	public static int getLatency(String functionalUnit) {
		switch(functionalUnit){
		case "Adder":
			return Adder.getExecutionCycle();
		case "Load":
			return Load.getExecutionCycle();
		case "Integer":
			return IntegerUnit.getExecutionCycle();
//		case "Divider":
//			Divider.assignDivider();
//		case "Multiplier":
//			Multiplier.assignMultiplier();
		}
		return 0;
	}
	
	public static void releaseUnit(String functionalUnit,String unitValue){
		switch(functionalUnit){
		case "Adder":
			Adder.releaseAdder(unitValue);;
			break;
		case "Load":
			Load.setLoadFree(true);
			break;
		case "Integer":
			IntegerUnit.setIntegerFree(true);
			break;
//		case "Divider":
//			Divider.assignDivider();
//		case "Multiplier":
//			Multiplier.assignMultiplier();
		}
	}

}
