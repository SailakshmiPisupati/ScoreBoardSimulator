package functionunits;

import opcodes.Instruction;
import operands.Register;
import pipelinestages.Execute;
import pipelinestages.Issue;
import pipelinestages.Write;
import scoreboardstatus.OutputStatus;
import scoreboardstatus.RegisterStatus;
import simulator.ScoreBoard;

public class WriteUnit {
	
	public static boolean isWriteBusy = false;

	public static boolean isWriteBusy() {
		return isWriteBusy;
	}

	public static void setWriteBusy(boolean isWriteBusy) {
		WriteUnit.isWriteBusy = isWriteBusy;
	}
	
	
	public static void execute(int startId) throws Exception{
		setWriteBusy(true);
		RegisterStatus.writeValuesToRegisters();							//write data to registers.
	}
}
