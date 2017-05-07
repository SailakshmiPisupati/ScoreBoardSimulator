package opcodes;

import java.util.ArrayList;

import operands.*;
import scoreboardstatus.RegisterStatus;

public class DADDI extends Instruction{
	Register destinationRegister;
	Register sourceRegister1;
	Immediates immediateOperand;
	
	public DADDI(Register destinationRegister, Register sourceRegister1, Immediates immediateOperand) {
		super();
		this.destinationRegister = destinationRegister;
		this.sourceRegister1 = sourceRegister1;
		this.immediateOperand = immediateOperand;
	}

	@Override
	public void execute() throws Exception {
		double value = RegisterStatus.read(this.sourceRegister1) + this.immediateOperand.value;
		RegisterStatus.write(this.destinationRegister, value);
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		return this.destinationRegister;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> sourceRegisterList = new ArrayList<Register>();
		sourceRegisterList.add(this.sourceRegister1);
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