package opcodes;

import java.util.ArrayList;

import operands.*;
import scoreboardstatus.RegisterStatus;

public class DSUBI extends Instruction{
	Register register_operand1;
	Register register_operand2;
	Immediates immediate_operand;
	
	public DSUBI(Register register_operand1, Register register_operand2, Immediates immediate_operand) {
		super();
		this.register_operand1 = register_operand1;
		this.register_operand2 = register_operand2;
		this.immediate_operand = immediate_operand;
	}

	@Override
	public void execute() throws Exception {
		double value = RegisterStatus.read(this.register_operand2) - this.immediate_operand.value;
		RegisterStatus.write(this.register_operand1, value);
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return this.register_operand1;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> source_registers = new ArrayList<Register>();
		source_registers.add(this.register_operand2);
		return null;
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
		return this.immediate_operand;
	}

}