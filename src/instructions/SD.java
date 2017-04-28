package instructions;

import java.util.ArrayList;

import instructions.operands.*;
import managers.Memory;
import managers.RegisterManager;

public class SD extends Instruction{
	RegisterOperand register_operand;
	MemoryOperand memory_operand;
	
	public SD(RegisterOperand ro, MemoryOperand mo) {
		this.register_operand = ro;
		this.memory_operand = mo;
	}

	@Override
	public void write() throws Exception {
		Memory.write(memory_operand.final_address(), "double", (int) register_operand.getValue());
	}

	@Override
	public void execute() throws Exception {
		// Do Nothing
	}

	@Override
	public RegisterOperand getDestinationRegister() throws Exception {
		return null;
	}

	@Override
	public ArrayList<RegisterOperand> getSourceRegisters() throws Exception {
		ArrayList<RegisterOperand> source_registers = new ArrayList<RegisterOperand>();
		source_registers.add(this.register_operand);
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