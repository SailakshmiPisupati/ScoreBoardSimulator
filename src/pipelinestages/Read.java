package pipelinestages;

import java.util.ArrayList;

import opcodes.BEQ;
import opcodes.BNE;
import opcodes.Instruction;
import operands.Register;
import scoreboardstatus.OutputStatus;
import scoreboardstatus.RegisterStatus;
import simulator.ScoreBoard;
import functionunits.FetchUnit;
import functionunits.IssueUnit;
import functionunits.ReadUnit;

// TODO Hazards to handle - RAW (ensure source registers are not being written)
public class Read {
	public static boolean rawHazard = false;
	public static ArrayList<Integer> readQueue = new ArrayList<Integer>();
	public static int readInstruction = 0;
	public static int isReadInstruction() {
		return readInstruction;
	}
	public static void setReadInstruction(int readInstruction) {
		Read.readInstruction = readInstruction;
	}
	public static void execute() throws Exception {
		IssueUnit.setIssueBusy(false);
		if(!ReadUnit.isReadBusy()){
			if(readQueue.size()!= 0){
				for(int i=0;i<readQueue.size();){
					int startId = readQueue.get(i);
					int newId = Fetch.instructionMapping.get(startId);
					Instruction instruction = ScoreBoard.instructions.get(newId);
					if(instruction instanceof BNE ||instruction instanceof BEQ ){
						FetchUnit.setFetchBusy(false);
						//IssueUnit.setIssueBusy(true);
					}
					if(areSourcesWritten(instruction)){
						OutputStatus.appendTo(startId, 6, 1);
						i++;
					}else{
						startId = readQueue.remove(i);
						ReadUnit.execute(startId);
						OutputStatus.append(startId, 3, ScoreBoard.clockCycle);
						break;
					}
				}	
			}		
		}	
	}
	private static boolean areSourcesWritten(Instruction instruction) throws Exception {
		
		ArrayList<Register> sourceReg = instruction.getSourceRegisters();
		//if the destination register and source register is the same..then no RAW hazard
		if(!sourceReg.isEmpty()){
			for(int i=0;i<sourceReg.size();i++){
				if(instruction.getDestinationRegister()!=null){
					if(!sourceReg.get(i).toString().equals(instruction.getDestinationRegister().toString()))
						if(RegisterStatus.checkIfRegisterIsBusy(sourceReg.get(i).toString())){
							rawHazard = true;
						}else{
							rawHazard = false;
						}
					else{
						rawHazard = false;
					}
				}else if(instruction.getDestinationRegister()==null){
					if(RegisterStatus.checkIfRegisterIsBusy(sourceReg.get(i).toString())){
						rawHazard = true;
					}else{
						rawHazard = false;
					}
					return rawHazard;
				}
			}
			return rawHazard;
		}else{
			return false;
		}
	}
}
