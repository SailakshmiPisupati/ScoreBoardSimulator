package cache;

import functionunits.ExecuteUnit;
import opcodes.Instruction;
import opcodes.LD;
import opcodes.LW;
import opcodes.SD;
import opcodes.SW;
import pipelinestages.Execute;
import scoreboardstatus.MemoryStatus;
import simulator.ScoreBoard;

public class DCache {
	public static boolean dCacheEnabled = true;
	public static int noOfSets = 2;
	public static int blocksPerSet = 2;
	public static int dcache[][];
	public static int lru[][];
	public static int DCacheAccessCount=0;
	public static int DCachehit =0;
	public static boolean DcacheBusy = false;
	public static boolean DCacheHit = false;
	
	public static boolean isDcacheBusy() {
		return DcacheBusy;
	}

	public static void setDcacheBusy(boolean dcacheBusy) {
		DcacheBusy = dcacheBusy;
	}

	public static void initializeDCache(){
		dcache= new int[getNoOfSets()][getBlocksPerSet()];
		lru= new int[getNoOfSets()][getBlocksPerSet()];
		for(int i =0; i<getNoOfSets();i++){
			for(int j=0;j<getBlocksPerSet();j++){
				dcache[i][j]=-1;
			}
		}
		for(int i =0; i<getNoOfSets();i++){
			for(int j=0;j<getBlocksPerSet();j++){
				lru[i][j]=-1;
			}
		}
	}
	
	public static void printCacheStatus(){
		System.out.println("************D-Cache status*************");
		for(int i=0; i<getNoOfSets();i++){
			for(int j=0;j<getBlocksPerSet();j++){
				System.out.print(dcache[i][j]+" ");
			}System.out.println();
		}
		System.out.println("***************************************");
	}
	
	public static void printLRUStatus(){
		System.out.println("************LRU status*************");	
		for(int i=0; i<getNoOfSets();i++){
			for(int j=0;j<getBlocksPerSet();j++){
				System.out.print(lru[i][j]+" ");
			}System.out.println();
		}
		System.out.println("***************************************");
	}
	
	public static boolean cacheLineBusy(){
		if(MemoryStatus.memoryReadByCaches){
			return true;
		}else{
			MemoryStatus.setMemoryReadByCaches(true);
			return false;
		}
	}
	
	public static void addToDCache(int memoryAddress, String type){
		if(cacheLineBusy()){
			
		}else{
			markDCacheAccess(type);
			System.out.println("Add to D-cache  "+memoryAddress);
			int cacheAddress = getCacheAddress(memoryAddress);
			int setId = getsetId(cacheAddress);
			int tag = getTag(cacheAddress);
			addValueToCache(tag, setId);
		}
	}
	
	public static void fetchFromDCache(int memoryAddress,String type){
//		if(cacheLineBusy()){
//		}else{
			markDCacheAccess(type);
			System.out.println("Cache data requested for memory address "+memoryAddress);
			int cacheAddress = getCacheAddress(memoryAddress);
			int setId = getsetId(cacheAddress);
			int tag = getTag(cacheAddress);
			
			int hit = checkDCache(tag,setId);
			System.out.println("Hits "+hit);
			//printCacheStatus();
			if(hit == 1){
				DCacheHit = true;
				MemoryStatus.setMemoryReadByCaches(false);
				
			}else if(Execute.dcacheAccessClock == 11){
				DCacheHit = false;
				addValueToCache(tag, setId);
				System.out.println("After adding value to cache");
				printCacheStatus();
			}
//		}
	}
	
	public static int checkDCache(int tag, int setId){
		int value = 0;
		for(int i=0;i<blocksPerSet;i++){
			if(dcache[setId][i]== tag){
				value = 1;
			}else{
				value = 0;
			}
		}
		return value;
	}
	
	public static int getsetId(int cacheAddress){
		return cacheAddress % noOfSets;
	}
	
	public static int getTag(int cacheAddress){
		return cacheAddress / noOfSets;
	}
	
	public static int getCacheAddress(int memoryAddress){
		int cacheAddress = memoryAddress - 256;
		cacheAddress = cacheAddress / 4;
		cacheAddress = cacheAddress +256;
		return cacheAddress;
	}
	
	public static void addValueToCache(int tag, int setId){
		int blockNumber = checkIfsetEmpty(setId);
		System.out.println("BlockNumber is "+blockNumber);
		dcache[setId][blockNumber]=tag;
		System.out.println(dcache[setId][blockNumber]);
		MemoryStatus.setMemoryReadByCaches(false);
	}
	
	public static int checkIfsetEmpty(int setId){
		int blockNumber = -1;
		for(int i=0;i<blocksPerSet;i++){
			if(dcache[setId][i]==-1){
				blockNumber = i;
			}
		}
		//if the cache is full, LRU policy is applied and the LRU block number is provided
		if(blockNumber == -1){
			//ScoreBoard.clockCycle = ScoreBoard.clockCycle +12;					//since the data is then added to the memory, it takes 12 clock cycles
			for(int i=0;i<blocksPerSet;i++){
				if(lru[setId][i]==-1){
					blockNumber = i;
				}
			}
			return blockNumber;
		}
		return blockNumber;
	}

	public static void markDCacheAccess(String type){
		if(type.equals("word")){
			
		}else if(type.equals("double")){
			
		}
	}

	public static int getNoOfSets() {
		return noOfSets;
	}


	public static void setNoOfSets(int noOfSets) {
		DCache.noOfSets = noOfSets;
	}


	public static int getBlocksPerSet() {
		return blocksPerSet;
	}


	public static void setBlocksPerSet(int blocksPerSet) {
		DCache.blocksPerSet = blocksPerSet;
	}
}
