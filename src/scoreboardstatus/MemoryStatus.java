package scoreboardstatus;
import java.util.TreeMap;

public class MemoryStatus {
	public static int startAddress = 0x100;
	public static int lastAddress;
	public static TreeMap<Integer, Integer> memoryLocations = new TreeMap<Integer, Integer>();

	public static int getcalculatedAddress(int address) {
		int new_address = calculateAddress(address);

		if(!memoryLocations.containsKey(new_address)) throw new Error("Invalid Memory address: " + address + " : " + new_address);

		return new_address;
	}
	public static int calculateAddress(int address){
		return ((address - 256) / 4 ) + 256;
	}

//	http://stackoverflow.com/questions/1735840/how-do-i-split-an-integer-into-2-byte-binary
	public static void writeToMemory(int address, String type, int value) {
		address = getcalculatedAddress(address);

		switch(type){
		case "word":
			memoryLocations.put(address, value);
			break;
		case "double":
			int value_0 = ((value >> 8) & 0xFF);
			int value_1 = (value & 0xFF);
			memoryLocations.put(address, value_0);
			memoryLocations.put(address+1, value_1);
			break;
		}
	}

	public static Integer readFromMemory(int address, String type) {
		address = getcalculatedAddress(address);

		int appendedDoubleLine = 0;

		switch(type){
		case "word":
			appendedDoubleLine = memoryLocations.get(address);
			break;
		case "double":
			int line1 = memoryLocations.get(address);
			int line2 = memoryLocations.get(address+1);
			appendedDoubleLine = ((line1 << 8) | (line2 & 0xFF));
			break;
		}

		return appendedDoubleLine;
	}
}
