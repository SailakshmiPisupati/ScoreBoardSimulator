package opcodes;

import java.util.ArrayList;

import operands.Immediates;
import operands.Memory;
import operands.Register;

public class J extends Instruction{
	public static String label;
	
	public J(String label) {
		super();
		this.label = label;
	}

	@Override
	public void execute() throws Exception {
		// Do Nothing
	}

	@Override
	public Register getDestinationRegister() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Register> getSourceRegisters() throws Exception {
		ArrayList<Register> sourceRegisterList = new ArrayList<Register>();
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