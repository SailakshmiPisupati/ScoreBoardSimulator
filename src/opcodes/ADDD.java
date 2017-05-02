package opcodes;

import java.util.ArrayList;

import operands.Immediates;
import operands.Memory;
import operands.Register;
import scoreboardstatus.RegisterStatus;

public class ADDD extends Instruction{
	Register register_operand1; // destination
	Register register_operand2; // source 1
	Register register_operand3; // source 2
			
	public ADDD(Register register_operand1, Register register_operand2, Register register_operand3) {
		super();
		this.register_operand1 = register_operand1;
		this.register_operand2 = register_operand2;
		this.register_operand3 = register_operand3;
	}
	
	@Override
	public void execute() throws Exception {
		double value = RegisterStatus.read(this.register_operand2) + RegisterStatus.read(this.register_operand3);
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
		source_registers.add(this.register_operand3);
		return source_registers;
	}

	@Override
	public void write() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Memory getMemory() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Immediates getImmediates() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}