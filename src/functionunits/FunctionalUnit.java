package functionunits;

import java.util.ArrayList;

public class FunctionalUnit {

	static ArrayList<String> adderUnit = new ArrayList<String>();

	public static boolean checkIfFunctionalUnitIsFree(String functionalUnit) {
		boolean isFree = false;
		switch(functionalUnit){
			case "Adder":
				isFree = Adder.checkIsFree();
				System.out.println("Adder is free");
				break;
			case "Divider":
				isFree = Divider.checkIsFree();
				System.out.println("Divider is free");
				break;
			case "Multiplier":
				isFree = Divider.checkIsFree();
				System.out.println("Adder is free");
				break;
		}
		
		return isFree;
	}
	
	public static void allocateAdders(){
		if(adderUnit.isEmpty()){
			return;
		}
		else{
			
			adderUnit.get(0);
			adderUnit.remove(0);
		}
	}
	
	public static void checkForFreeAdderUnit(){
		
	}
	
	public static void initializeAdders(){
		int noOfUnits = Adder.getNoOfUnits();
		
		for(int i=1; i<noOfUnits+1;i++){
			adderUnit.add("Adder"+i);
		}
	}

}
