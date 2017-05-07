package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import scoreboardstatus.MemoryStatus;

public class DataParser {

	public static void readFile(String filename)throws Exception {
		BufferedReader bufferedReader;
		String memoryLine;
		int count = 0;
		bufferedReader = new BufferedReader(new FileReader(filename));
		while((memoryLine = bufferedReader.readLine()) != null){
			memoryLine.trim();
			MemoryStatus.memoryLocations.put(MemoryStatus.startAddress + count, Integer.parseInt(memoryLine, 2));
			count++;
		}
		MemoryStatus.lastAddress = MemoryStatus.startAddress + count;
	}
}
