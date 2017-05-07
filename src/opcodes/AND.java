package opcodes;

import java.util.ArrayList;

import operands.Immediates;
import operands.Memory;
import operands.Register;
import scoreboardstatus.RegisterStatus;

public class AND extends Instruction{
	Register destinationRegister;
	Register sourceRegister1;
	Register sourceRegister2;

	public AND(Register destinationRegister, Register sourceRegister1, Register sourceRegister2) {
		super();
		this.destinationRegister = destinationRegister;
		this.sourceRegister1 = sourceRegister1;
		this.sourceRegister2 = sourceRegister2;
	}
	

	public void execute() throws Exception {
		double value = (int) RegisterStatus.read(this.sourceRegister1) & (int) RegisterStatus.read(this.sourceRegister2);
		RegisterStatus.write(this.destinationRegister, value);
	}

	public Register getDestinationRegister() throws Exception {
		return this.destinationRegister;
	}


	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> sourceRegisterList = new ArrayList<Register>();
		sourceRegisterList.add(this.sourceRegister1);
		sourceRegisterList.add(this.sourceRegister2);
		return sourceRegisterList;
	}

	public void writeToRegister() throws Exception {
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