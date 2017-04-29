package opcodes;

import java.util.ArrayList;

import operands.*;
import scoreboardstatus.RegisterStatus;

public class LUI extends Instruction{
	Register register_operand;
	Immediates immediate_operand;
	
	public LUI(Register ro, Immediates io) {
		this.register_operand = ro;
		this.immediate_operand = io;
	}

	@Override
	public void execute() {
		// Do Nothing
	}

	@Override
	public void write() throws Exception {
		register_operand.setValue(immediate_operand.value);
		RegisterStatus.write(register_operand, immediate_operand.value);
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
	public Memory getMemoryOperand() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Immediates getImmediateOperand() throws Exception {
		return this.immediate_operand;
	}

}