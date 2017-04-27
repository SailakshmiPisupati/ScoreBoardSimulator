package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.HashMap;

import functionunits.Adder;
import functionunits.Divider;
import functionunits.Multiplier;
import simulator.ScoreBoard;

public class ConfigParser {

	public static void readFile(String filename) {
		BufferedReader bufferedReader;
		FileReader fileReader;
		
		try{
			String configLine;
			
			int instructionCount = 0;
			LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filename));
			while(lineNumberReader.readLine()!=null){
				instructionCount++;
			}
			
			lineNumberReader.close();
			//String config[]=new String[instructionCount];
						
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
			
		}catch(Exception e){
			System.out.println("Exception "+ e);
		}	
	}
	//TODO read config file
	
}