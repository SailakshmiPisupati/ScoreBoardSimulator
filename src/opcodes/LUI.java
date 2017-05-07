package opcodes;

import java.util.ArrayList;

import operands.*;
import scoreboardstatus.RegisterStatus;

public class LUI extends Instruction{
	Register registerOperand;
	Immediates immediateOperand;
	
	public LUI(Register ro, Immediates io) {
		this.registerOperand = ro;
		this.immediateOperand = io;
	}

	@Override
	public void execute() {
		// Do Nothing
	}

	@Override
	public void writeToRegister() throws Exception {
		registerOperand.setValue(immediateOperand.value);
		RegisterStatus.write(registerOperand, immediateOperand.value);
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return this.registerOperand;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> sourceRegisterList = new ArrayList<Register>();
		return sourceRegisterList;
	}

	@Override
	public Memory getMemory() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Immediates getImmediates() throws Exception {
		return this.immediateOperand;
	}

}