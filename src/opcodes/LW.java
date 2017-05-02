package opcodes;

import java.util.ArrayList;

import operands.*;
import scoreboardstatus.MemoryStatus;
import scoreboardstatus.RegisterStatus;

public class LW extends Instruction{
	Register register_operand;
	Memory memory_operand;

	public LW(Register ro, Memory mo) {
		this.register_operand = ro;
		this.memory_operand = mo;
	}

	@Override
	public void execute() {
		// Do Nothing
	}

	@Override
	public void write() throws Exception {
		double value = MemoryStatus.read(memory_operand.final_address(), "word");
		register_operand.setValue(value);
		RegisterStatus.write(register_operand, value);
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return this.register_operand;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> source_registers = new ArrayList<Register>();
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