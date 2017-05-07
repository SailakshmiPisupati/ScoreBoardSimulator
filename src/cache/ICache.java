package cache;

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
			while(blockNumber >3){
				blockNumber = blockNumber/getNumberOfBlocks();
			}
		}
		return blockNumber;
	}
	
	public static int getOffset(int instruction){
		int offset =0 ;
		offset = instruction % getBlockSize();
		if(offset > 3){
			while(offset>3){
				offset = offset/getBlockSize();
			}
		}
		return offset;
	}
	
	public static int getTag(int instruction){
		int tag = Integer.parseInt(Character.toString(Integer.toBinaryString(instruction).charAt(0)));
		return tag;
	}
	public static void writeToICache(int instruction){
		for(int i=0;i<numberOfBlocks;i++){
			for(int j=0;j<blockSize;j++){
				icache[getBlockNumber(instruction)][getOffset(instruction)]=getTag(instruction);
			}
		}
	}
	
	public static boolean readFromICache(int instruction){	
		ICacheAccessedCount++;
		if(icache[getBlockNumber(instruction)][getOffset(instruction)]==getTag(instruction)){
			System.out.println("Number of icache hits "+hits);
			hits++;
			return true;
		}else{
			System.out.println("Number of icache miss "+miss);
			return false;
		}	
	}
	
	public static void fetchNextInstructions(int instruction){
		
			writeToICache(instruction);
			ScoreBoard.clockCycle++;ScoreBoard.clockCycle++;ScoreBoard.clockCycle++;	//memory access is 3
//			instruction++;
//		}
		//printCacheStatus();
	}
	
	public static void printCacheStatus(){
		System.out.println("Cache status");
		for(int i=0;i<numberOfBlocks;i++){
			for(int j=0;j<blockSize;j++){
				System.out.print(icache[i][j]+" ");
			}System.out.println();
		}
	}
	
}

