package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.HashMap;

import functionunits.Adder;
import functionunits.Divider;
import functionunits.FunctionalUnit;
import functionunits.IntegerUnit;
import functionunits.Load;
import functionunits.Multiplier;
import simulator.ScoreBoard;

public class ConfigParser {

	public static void readFile(String filename)throws Exception {
		BufferedReader bufferedReader;
		String configLine;
					
		int count = 1;
		String[] unitName = null ;
		bufferedReader = new BufferedReader(new FileReader(filename));
		while((configLine = bufferedReader.readLine()) != null){
			
			unitName = configLine.split(":");
			String[] functionalUnit = unitName[1].split(",");
			switch(unitName[0].toUpperCase()){
				
				case "FP ADDER" :
					Adder.setNoOfUnits(Integer.parseInt(functionalUnit[0].trim()));
					Adder.setExecutionCycle(Integer.parseInt(functionalUnit[1].trim()));
					break;
				case "FP MULTIPLIER" :
					Multiplier.setNoOfUnits(Integer.parseInt(functionalUnit[0].trim()));
					Multiplier.setExecutionCycle(Integer.parseInt(functionalUnit[1].trim()));
					break;
				case "FP DIVIDER" :
					Divider.setNoOfUnits(Integer.parseInt(functionalUnit[0].trim()));
					Divider.setExecutionCycle(Integer.parseInt(functionalUnit[1].trim()));
					break;
			}	
			count++;
		}
		initializeFunctionalUnits();
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