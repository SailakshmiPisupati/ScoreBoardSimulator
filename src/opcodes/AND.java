package opcodes;

import java.util.ArrayList;

import operands.Immediates;
import operands.Memory;
import operands.Register;
import scoreboardstatus.RegisterStatus;

public class AND extends Instruction{
	Register register_operand1;
	Register register_operand2;
	Register register_operand3;

	public AND(Register register_operand1, Register register_operand2, Register register_operand3) {
		super();
		this.register_operand1 = register_operand1;
		this.register_operand2 = register_operand2;
		this.register_operand3 = register_operand3;
	}
	

	public void execute() throws Exception {
		double value = (int) RegisterStatus.read(this.register_operand2) & (int) RegisterStatus.read(this.register_operand3);
		RegisterStatus.write(this.register_operand1, value);
	}

	public Register getDestinationRegister() throws Exception {
		return this.register_operand1;
	}


	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> source_registers = new ArrayList<Register>();
		source_registers.add(this.register_operand2);
		source_registers.add(this.register_operand3);
		return source_registers;
	}

	public void write() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Memory getMemory() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Immediates getImmediates() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}