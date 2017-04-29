package opcodes;

import java.util.ArrayList;

import operands.*;
import scoreboardstatus.RegisterStatus;

public class J extends Instruction{
	public String label;
	
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
		ArrayList<Register> source_registers = new ArrayList<Register>();
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