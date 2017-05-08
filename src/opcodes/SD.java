package opcodes;

import java.util.ArrayList;

import cache.DCache;
import operands.*;
import scoreboardstatus.MemoryStatus;

public class SD extends Instruction{
	Register registerOperand;
	Memory memoryOperand;

	public SD(Register ro, Memory mo) {
		this.registerOperand = ro;
		this.memoryOperand = mo;
	}

	@Override
	public void writeToRegister() throws Exception {
		if(DCache.dCacheEnabled){
			DCache.addToDCache(memoryOperand.calculateOffset(),"double");
		}else{
			MemoryStatus.writeToMemory(memoryOperand.calculateOffset(), "double", (int) registerOperand.getValue());
		}
	}

	@Override
	public void execute() throws Exception {
		// Do Nothing
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return null;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> sourceRegisterList = new ArrayList<Register>();
		sourceRegisterList.add(registerOperand);
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