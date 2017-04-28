package instructions;

import java.util.ArrayList;

import instructions.operands.*;
import managers.Memory;
import managers.RegisterManager;

public class LW extends Instruction{
	RegisterOperand register_operand;
	MemoryOperand memory_operand;
	
	public LW(RegisterOperand ro, MemoryOperand mo) {
		this.register_operand = ro;
		this.memory_operand = mo;
	}

	@Override
	public void execute() {
		// Do Nothing
	}

	@Override
	public void write() throws Exception {
		double value = Memory.read(memory_operand.final_address(), "word");
		register_operand.setValue(value);
		RegisterManager.write(register_operand, value);
	}

	@Override
	public RegisterOperand getDestinationRegister() throws Exception {
		return this.register_operand;
	}

	@Override
	public ArrayList<RegisterOperand> getSourceRegisters() throws Exception {
		ArrayList<RegisterOperand> source_registers = new ArrayList<RegisterOperand>();
		return source_registers;
	}

	@Override
	public MemoryOperand getMemoryOperand() throws Exception {
		return this.memory_operand;
	}

	@Override
	public ImmediateOperand getImmediateOperand() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}