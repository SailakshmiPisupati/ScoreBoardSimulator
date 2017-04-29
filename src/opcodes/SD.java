package opcodes;

import java.util.ArrayList;

import operands.*;
import scoreboardstatus.MemoryStatus;
import scoreboardstatus.RegisterStatus;

public class SD extends Instruction{
	Register register_operand;
	Memory memory_operand;
	
	public SD(Register ro, Memory mo) {
		this.register_operand = ro;
		this.memory_operand = mo;
	}

	@Override
	public void write() throws Exception {
		MemoryStatus.write(memory_operand.final_address(), "double", (int) register_operand.getValue());
	}

	@Override
	public void execute() throws Exception {
		// Do Nothing
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return null;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> source_registers = new ArrayList<Register>();
		source_registers.add(this.register_operand);
		return source_registers;
	}

	@Override
	public Memory getMemoryOperand() throws Exception {
		return this.memory_operand;
	}

	@Override
	public Immediates getImmediateOperand() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}