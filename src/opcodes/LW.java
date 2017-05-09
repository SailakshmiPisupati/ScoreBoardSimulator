package opcodes;

import java.util.ArrayList;

import cache.DCache;
import operands.*;
import scoreboardstatus.MemoryStatus;
import scoreboardstatus.RegisterStatus;

public class LW extends Instruction{
	Register registerOperand;
	Memory memoryOperand;

	public LW(Register ro, Memory mo) {
		this.registerOperand = ro;
		this.memoryOperand = mo;
	}

	@Override
	public void execute() {
		// Do Nothing
	}

	@Override
	public void writeToRegister() throws Exception {
		
		if(DCache.dCacheEnabled){
			//checkfrom cache
			DCache.fetchFromDCache(memoryOperand.calculateOffset(),"word");
			double value = MemoryStatus.readFromMemory(memoryOperand.calculateOffset(), "word");
			registerOperand.setValue(value);
			RegisterStatus.write(registerOperand, value);
			
		}else{
			double value = MemoryStatus.readFromMemory(memoryOperand.calculateOffset(), "word");
			registerOperand.setValue(value);
			RegisterStatus.write(registerOperand, value);
			
		}
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return this.registerOperand;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> sourceRegisterList = new ArrayList<Register>();
		if(memoryOperand.baseRegister != null) sourceRegisterList.add(memoryOperand.baseRegister);
		return sourceRegisterList;
	}

	@Override
	public Memory getMemory() throws Exception {
		return this.memoryOperand;
	}

	@Override
	public Immediates getImmediates() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}