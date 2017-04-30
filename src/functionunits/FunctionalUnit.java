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
			case "Adder":
				isFree = (adderUnit.size() == Adder.getNoOfUnits())?true : false; 
				System.out.println("Adder is free");
				break;
			case "Divider":
				isFree = (dividerUnit.size() == Divider.getNoOfUnits())?true : false;
				System.out.println("Divider is free");
				break;
			case "Multiplier":
				isFree = (multiplierUnit.size() == Multiplier.getNoOfUnits())?true : false;
				System.out.println("Adder is free");
				break;
		}
		
		return isFree;
	}

}
