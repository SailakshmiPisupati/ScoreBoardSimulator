package instructions;

import java.util.ArrayList;

import managers.MemoryManager;
import managers.RegisterManager;
import instructions.Operands.*;

public class LD extends Instruction{
	RegisterOperand register_operand;
	MemoryOperand memory_operand;
	
	public LD(RegisterOperand ro, MemoryOperand mo) {
		this.register_operand = ro;
		this.memory_operand = mo;
	}

	@Override
	public void write() throws Exception {
		double value = MemoryManager.read(memory_operand.final_address(), "double");
		register_operand.setValue(value);
		RegisterManager.write(register_operand, value);
	}

	@Override
	public void execute() {
		// Do Nothing
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