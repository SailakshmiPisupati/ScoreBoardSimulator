package opcodes;

import java.util.ArrayList;

import cache.DCache;
import operands.*;
import scoreboardstatus.MemoryStatus;
import scoreboardstatus.RegisterStatus;

public class LD extends Instruction{
	Register registerOperand;
	Memory memoryOperand;

	public LD(Register ro, Memory mo) {
		this.registerOperand = ro;
		this.memoryOperand = mo;
	}

	@Override
	public void writeToRegister() throws Exception {
		
		if(DCache.dCacheEnabled){
			DCache.fetchFromDCache(memoryOperand.calculateOffset(),"double");
			System.out.println("after cache calculation -- "+memoryOperand.baseRegister.getValue());
			double value = MemoryStatus.readFromMemory(memoryOperand.calculateOffset(), "double");
			registerOperand.setValue(value);
			RegisterStatus.write(registerOperand, value);
		}else{
			double value = MemoryStatus.readFromMemory(memoryOperand.calculateOffset(), "double");
			registerOperand.setValue(value);
			RegisterStatus.write(registerOperand, value);
			
		}
		
		
	}

	@Override
	public void execute() {
		// Do Nothing
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