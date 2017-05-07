package opcodes;

import java.util.ArrayList;

import operands.*;

public class LI extends Instruction{
	Register registerOperand;
	Immediates immediateOperand;
	
	public LI(Register ro, Immediates io) {
		this.registerOperand = ro;
		this.immediateOperand = io;
	}

	@Override
	public void writeToRegister() throws Exception {
		System.out.println("Immediate operand "+immediateOperand.getValue());
		registerOperand.setValue(immediateOperand.getValue());
		System.out.println("Register value "+registerOperand.getValue());
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