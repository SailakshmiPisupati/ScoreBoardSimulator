package opcodes;

import java.util.ArrayList;

import operands.*;
import scoreboardstatus.RegisterStatus;

public class SUBD extends Instruction{
	Register register_operand1;
	Register register_operand2;
	Register register_operand3;

	public SUBD(Register register_operand1, Register register_operand2, Register register_operand3) {
		super();
		this.register_operand1 = register_operand1;
		this.register_operand2 = register_operand2;
		this.register_operand3 = register_operand3;
	}
	
	@Override
	public void execute() {
		// Do Nothing
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return this.register_operand1;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> source_registers = new ArrayList<Register>();
		source_registers.add(this.register_operand2);
		source_registers.add(this.register_operand3);
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