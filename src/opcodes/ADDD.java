package opcodes;

import java.util.ArrayList;

import operands.Immediates;
import operands.Memory;
import operands.Register;
import scoreboardstatus.RegisterStatus;

public class ADDD extends Instruction{
	Register destinationRegister;
	Register sourceRegister1;
	Register sourceRegister2;
			
	public ADDD(Register destinationRegister, Register sourceRegister1, Register sourceRegister2) {
		super();
		this.destinationRegister = destinationRegister;
		this.sourceRegister1 = sourceRegister1;
		this.sourceRegister2 = sourceRegister2;
	}
	
	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> sourceRegisterList = new ArrayList<Register>();
		sourceRegisterList.add(this.sourceRegister1);
		sourceRegisterList.add(this.sourceRegister2);
		return sourceRegisterList;
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return this.destinationRegister;
	}

	@Override
	public Immediates getImmediates() throws Exception {
		return null;
	}

	@Override
	public void writeToRegister() throws Exception {
		
	}

	@Override
	public Memory getMemory() throws Exception {
		return null;
	}

	@Override
	public void execute() throws Exception {
		double value = RegisterStatus.read(this.sourceRegister1) + RegisterStatus.read(this.sourceRegister2);
		RegisterStatus.write(this.destinationRegister, value);
	}

}