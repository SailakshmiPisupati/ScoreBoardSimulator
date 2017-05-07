package opcodes;

import java.util.ArrayList;

import operands.Immediates;
import operands.Memory;
import operands.Register;

public class SUBD extends Instruction{
	Register destinationRegister;
	Register sourceRegister1;
	Register sourceRegister2;

	public SUBD(Register destinationRegister, Register sourceRegister1, Register sourceRegister2) {
		super();
		this.destinationRegister = destinationRegister;
		this.sourceRegister1 = sourceRegister1;
		this.sourceRegister2 = sourceRegister2;
	}
	
	@Override
	public void execute() {
		// Do Nothing
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return this.destinationRegister;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> sourceRegisterList = new ArrayList<Register>();
		sourceRegisterList.add(this.sourceRegister1);
		sourceRegisterList.add(this.sourceRegister2);
		return sourceRegisterList;
	}

	@Override
	public void writeToRegister() throws Exception {
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