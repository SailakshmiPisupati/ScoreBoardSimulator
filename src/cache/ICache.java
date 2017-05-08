package cache;

import scoreboardstatus.MemoryStatus;
import simulator.ScoreBoard;

public class ICache {
	public static int numberOfBlocks;
	public static int blockSize;
	public static int icache[][];
	public static int hits =0;
	public static int miss =0;
	public static boolean enableIcache = true;
	public static int ICacheAccessedCount =0;
	public static int ICachePenaltyCycles = 3;
	
	public static int getNumberOfBlocks() {
		return numberOfBlocks;
	}

	public static void setNumberOfBlocks(int numberOfBlocks) {
		ICache.numberOfBlocks = numberOfBlocks;
	}

	public static int getBlockSize() {
		return blockSize;
	}

	public static void setBlockSize(int blockSize) {
		ICache.blockSize = blockSize;
	}

	public static void initializeICache(){
		icache= new int[getNumberOfBlocks()][getBlockSize()];
		for(int i=0;i<numberOfBlocks;i++){
			for(int j=0;j<blockSize;j++){
				icache[i][j] = -1;
			}
		}
	}
	public static int getBlockNumber(int instruction){
		int blockNumber =0 ;
		blockNumber = instruction /getNumberOfBlocks();
		if(blockNumber >3){
			blockNumber = blockNumber / getBlockSize();
			blockNumber = blockNumber % getNumberOfBlocks();
		}
		return blockNumber;
	}
	
	public static int getOffset(int instruction){
		int offset =0 ;
		offset = instruction % getBlockSize();
		return offset;
	}
	
	public static int getTag(int instruction){
		int tag = Integer.parseInt(Character.toString(Integer.toBinaryString(instruction).charAt(0)));
		return tag;
	}
	public static void writeToICache(int instruction){
//		for(int i=0;i<numberOfBlocks;i++){
//			for(int j=0;j<blockSize;j++){
//				
//			}
//		}
		icache[getBlockNumber(instruction)][getOffset(instruction)]=getTag(instruction);
	}
	
	public static boolean readFromICache(int instruction){	
		if(icache[getBlockNumber(instruction)][getOffset(instruction)]==getTag(instruction)){
			ICacheAccessedCount++;hits++;
			return true; 
		}else{
			return false;
		}	
	}
	
	public static void fetchNextInstructions(int instruction){
		writeToICache(instruction);
		//ScoreBoard.clockCycle++;ScoreBoard.clockCycle++;ScoreBoard.clockCycle++;	//memory access is 3
	}
	
	public static void printCacheStatus(){
		System.out.println("I-Cache status");
		for(int i=0;i<numberOfBlocks;i++){
			for(int j=0;j<blockSize;j++){
				System.out.print(icache[i][j]+" ");
			}System.out.println();
		}
	}
	
}

