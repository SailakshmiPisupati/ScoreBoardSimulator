package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.HashMap;
import java.util.TreeMap;

import scoreboardstatus.MemoryStatus;
import simulator.ScoreBoard;

public class DataParser {

	public static void readFile(String filename) {
		BufferedReader bufferedReader;
		FileReader fileReader;
		
		try{
			String memoryLine;
			
			int lineCount = 0;
			LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filename));
			while(lineNumberReader.readLine()!=null){
				lineCount++;
			}
			
			lineNumberReader.close();
			
			int count = 1;
			bufferedReader = new BufferedReader(new FileReader(filename));
			while((memoryLine = bufferedReader.readLine()) != null){
				MemoryStatus.memory.put(MemoryStatus.start_address + count, Integer.parseInt(memoryLine, 2));
				count++;
			}
			
			
		}catch(Exception e){
			System.out.println("Exception "+ e);
		}	
	}
}
