package functionunits;

import opcodes.BEQ;
import opcodes.BNE;
import opcodes.HLT;
import opcodes.Instruction;
import pipelinestages.Execute;
import pipelinestages.Fetch;
import pipelinestages.Read;
import simulator.ScoreBoard;

public class ReadUnit {
	
	public static boolean isReadBusy=false;

	public static boolean isReadBusy() {
		return isReadBusy;
	}

	public static void setReadBusy(boolean isReadBusy) {
		ReadUnit.isReadBusy = isReadBusy;
	}
	
	public static void execute(int count){
		setReadBusy(true);
		Instruction instruction = ScoreBoard.instructions.get(count);
		if(instruction instanceof BNE){
//			TODO
//			if condition passes{
//				change fetch stages's count
//			}
		}else if(instruction instanceof BEQ){
//			TODO
//			if condition passes{
//				change fetch stages's count
//			}
		}else{
			Execute.executeQueue.add(count);			
		}
		Read.readQueue.clear();
		
	}

}
