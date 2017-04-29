package opcodes;

import java.util.ArrayList;

import operands.*;
import scoreboardstatus.RegisterStatus;

// Data Transfers - LW, SW, L.D, S.D
// Arithmetic/ logical - DADD, DADDI, DSUB, DSUBI, AND, ANDI, OR, ORI, LI, LUI, ADD.D, MUL.D, DIV.D, SUB.D
// Control - J, BEQ, BNE
// Special purpose - HLT (to stop fetching new instructions)

public abstract class Instruction {
	public abstract void execute() throws Exception;
	public abstract void write() throws Exception;
	public abstract Register getDestinationRegister() throws Exception;
	public abstract ArrayList<Register> getSourceRegisters() throws Exception;
	public abstract Memory getMemoryOperand() throws Exception;
	public abstract Immediates getImmediateOperand() throws Exception;
	public String label;

	@Override
	public String toString() {
		try {
			StringBuilder s =  new StringBuilder((this.label == null ? "" : this.label + ":") + this.getClass().getSimpleName());
			if(this.getDestinationRegister() != null) s.append(" " + this.getDestinationRegister().toString());
			
			for (Register source_operand: this.getSourceRegisters())
				s.append(", " + source_operand);		
			if(this.getMemoryOperand() != null) s.append(", " + this.getMemoryOperand().toString());
			if(this.getImmediateOperand() != null) s.append(", " + this.getImmediateOperand().toString());

			return s.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void markDestinationRegisterStatus() throws Exception{
		if(this.getDestinationRegister() != null) this.getDestinationRegister().setWriteStatus(true);
	}
	
//	public void markSourceRegisterStatus() throws Exception{
//		for (RegisterOperand source_operand: this.getSourceRegisters())
//			source_operand.setReadStatus(true);
//	}

	public void unMarkRegisterStatus() throws Exception{
		Register destination_operand = this.getDestinationRegister();
		if(destination_operand != null) destination_operand.setWriteStatus(false);

//		for (RegisterOperand source_operand: this.getSourceRegisters()) {
//			source_operand.setReadStatus(false);
//		}
	};

//	public boolean isDestinationBeingRead() throws Exception{
//		RegisterOperand destination_operand = this.getDestinationRegister();
//		if(destination_operand != null) return destination_operand.isBeingRead();
//		return false;
//	};

	public boolean isDestinationBeingWritten() throws Exception{
		Register destination_operand = this.getDestinationRegister();
		if(destination_operand != null) return destination_operand.isBeingWritten();
		return false;
	};

	public boolean areSourcesBeingWritten() throws Exception{
		for (Register source_operand: this.getSourceRegisters()) {
			if(source_operand.isBeingWritten()) return true;
		}
		return false;		
	};
}
