package opcodes;

import java.util.ArrayList;

import operands.*;
import scoreboardstatus.MemoryStatus;

public class SW extends Instruction{
	Register registerOperand;
	Memory memoryOperand;

	public SW(Register ro, Memory mo) {
		this.registerOperand = ro;
		this.memoryOperand = mo;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	public void writeToRegister() throws Exception {
		MemoryStatus.writeToMemory(memoryOperand.calculateOffset(), "word", (int) registerOperand.getValue());
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