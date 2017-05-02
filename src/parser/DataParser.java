package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import scoreboardstatus.MemoryStatus;

public class DataParser {

	public static void readFile(String filename)throws Exception {
		BufferedReader bufferedReader;
		String memoryLine;
		int count = 1;
		bufferedReader = new BufferedReader(new FileReader(filename));
		while((memoryLine = bufferedReader.readLine()) != null){
			MemoryStatus.memory.put(MemoryStatus.start_address + count, Integer.parseInt(memoryLine, 2));
			count++;
		}	
	}
}
