package stages;

import instructionopcodes.Instruction;

public class ReadStage {
	public static int readInstruction(String instruction){
//		System.out.println("Instruction number "+instruction+" at Read Stage");
		
		
		String className = Instruction.execute(instruction);
		//execute
		return 1;
	}

	
}
