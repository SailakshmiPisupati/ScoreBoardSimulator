package opcodes;

import java.util.ArrayList;

import operands.*;

public class LI extends Instruction{
	Register register_operand;
	Immediates immediate_operand;
	
	public LI(Register ro, Immediates io) {
		this.register_operand = ro;
		this.immediate_operand = io;
	}

	@Override
	public void write() throws Exception {
		register_operand.setValue(immediate_operand.value);
	}

	@Override
	public void execute() {
		// Do Nothing
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return this.register_operand;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> source_registers = new ArrayList<Register>();
		return source_registers;
	}

	@Override
	public Memory getMemory() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Immediates getImmediates() throws Exception {
		return this.immediate_operand;
	}

}