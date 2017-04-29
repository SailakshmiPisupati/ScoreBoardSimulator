package opcodes;

import java.util.ArrayList;

import operands.*;

public class BNE extends Instruction{
	Register register_operand1;
	Register register_operand2;
	public String label;
	
	public BNE(Register register_operand1, Register register_operand2, String label) {
		super();
		this.register_operand1 = register_operand1;
		this.register_operand2 = register_operand2;
		this.label = label.trim();
	}

	public boolean isConditionSatisfied() throws Exception {
		return register_operand1.getValue() != register_operand2.getValue();	
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return null;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> source_registers = new ArrayList<Register>();
		source_registers.add(this.register_operand1);
		source_registers.add(this.register_operand2);
		return source_registers;
	}

	@Override
	public void write() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Memory getMemoryOperand() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Immediates getImmediateOperand() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}