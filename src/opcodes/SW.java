package opcodes;

import java.util.ArrayList;

import operands.*;
import scoreboardstatus.MemoryStatus;

public class SW extends Instruction{
	Register register_operand;
	Memory memory_operand;

	public SW(Register ro, Memory mo) {
		this.register_operand = ro;
		this.memory_operand = mo;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
	}

	@Override
	public void write() throws Exception {
		MemoryStatus.write(memory_operand.final_address(), "word", (int) register_operand.getValue());
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return null;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> source_registers = new ArrayList<Register>();
		source_registers.add(register_operand);
		if(memory_operand.base_register != null) source_registers.add(memory_operand.base_register);
		return source_registers;
	}

	@Override
	public Memory getMemory() throws Exception {
		return this.memory_operand;
	}

	@Override
	public Immediates getImmediates() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}