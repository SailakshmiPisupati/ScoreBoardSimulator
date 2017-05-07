package opcodes;

import java.util.ArrayList;

import operands.Immediates;
import operands.Memory;
import operands.Register;

public class BEQ extends Instruction{
	Register destinationRegister;
	Register sourceRegister1;
	public static String label;
	
	public BEQ(Register destinationRegister, Register sourceRegister1, String label) {
		super();
		this.destinationRegister = destinationRegister;
		this.sourceRegister1 = sourceRegister1;
		this.label = label;
	}

	public boolean checkBranchCondition() throws Exception {
		return destinationRegister.getValue() == sourceRegister1.getValue();	
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
		ArrayList<Register> sourceRegisterList = new ArrayList<Register>();
		sourceRegisterList.add(this.destinationRegister);
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