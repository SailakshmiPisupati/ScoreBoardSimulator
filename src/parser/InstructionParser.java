package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import opcodes.*;
import operands.*;
import scoreboardstatus.RegisterStatus;
import simulator.ScoreBoard;

public class InstructionParser {
	private static BufferedReader bufferedReader;

	public static void readFile(String filename) throws Exception {
		try{
			String line;	
			bufferedReader = new BufferedReader(new FileReader(filename));
			int count = 0;
			while((line = bufferedReader.readLine()) != null){
				String label = null;
				
				if(line.contains(":")){	
					String[] tokens = line.split(":");					
					label = tokens[0];
					line = tokens[1];
				}
				
				String[] tokens = line.trim().split("[\\s]", 2);
				String opcode = tokens[0].trim().toUpperCase().replace(".", "");		
				String[] operands = new String[0];
				
				if(tokens.length > 1){
					operands = tokens[1].replaceAll("\\s+", "").trim().split(",");
				}	
				Instruction instruction = createInstruction(opcode, operands, label);
				
				if(label != null){
					ScoreBoard.labelLocation.put(label, count);
				}
				ScoreBoard.instructions.put(count++, instruction);
			}
			RegisterStatus.initializeRegisters();
		}catch(IOException e){
			System.out.println("Exception "+ e);
		}
	}
	
	private static Instruction createInstruction(String opcode, String[] operands, String label) throws Exception {
		Instruction instruction = null;
		Immediates immediateOperand = null;
		Memory memoryOperand = null;
		Register destinationRegister = null, sourceRegister1 = null, sourceRegister2 = null;

		switch(opcode){
		case "LW":
			destinationRegister = new Register(operands[0]);
			memoryOperand = new Memory(operands[1]);
			instruction = new LW(destinationRegister, memoryOperand);
			break;
		case "LD":
			destinationRegister = new Register(operands[0]);
			memoryOperand = new Memory(operands[1]);
			instruction = new LD(destinationRegister, memoryOperand);
			break;
		case "LI":
			destinationRegister = new Register(operands[0]);
			immediateOperand = new Immediates(operands[1]);
			instruction = new LI(destinationRegister, immediateOperand);
			break;
		case "LUI": // Load upper Immediate
			destinationRegister = new Register(operands[0]);
			immediateOperand = new Immediates(operands[1]);
			instruction = new LUI(destinationRegister, immediateOperand);
			break;
		case "SW": // Store Word
			destinationRegister = new Register(operands[0]);
			memoryOperand = new Memory(operands[1]);
			instruction = new SW(destinationRegister, memoryOperand);
			break;
		case "SD":
			destinationRegister = new Register(operands[0]);
			memoryOperand = new Memory(operands[1]);
			instruction = new SD(destinationRegister, memoryOperand);
			break;
		case "DADD":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			sourceRegister2 = new Register(operands[2]);
			instruction = new DADD(destinationRegister, sourceRegister1, sourceRegister2);
			break;
		case "DADDI":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			immediateOperand = new Immediates(operands[2]);
			instruction = new DADDI(destinationRegister, sourceRegister1, immediateOperand);
			break;
		case "DSUB":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			sourceRegister2 = new Register(operands[2]);
			instruction = new DSUB(destinationRegister, sourceRegister1, sourceRegister2);
			break;
		case "DSUBI":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			immediateOperand = new Immediates(operands[2]);
			instruction = new DSUBI(destinationRegister, sourceRegister1, immediateOperand);
			break;
		case "AND":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			sourceRegister2 = new Register(operands[2]);
			instruction = new AND(destinationRegister, sourceRegister1, sourceRegister2);
			break;
		case "ANDI":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			immediateOperand = new Immediates(operands[2]);
			instruction = new ANDI(destinationRegister, sourceRegister1, immediateOperand);
			break;
		case "OR":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			sourceRegister2 = new Register(operands[2]);
			instruction = new OR(destinationRegister, sourceRegister1, sourceRegister2);
			break;
		case "ORI":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			immediateOperand = new Immediates(operands[2]);
			instruction = new ORI(destinationRegister, sourceRegister1, immediateOperand);
			break;
		case "ADDD":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			sourceRegister2 = new Register(operands[2]);
			instruction = new ADDD(destinationRegister, sourceRegister1, sourceRegister2);
			break;
		case "MULD":
		case "MULTD":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			sourceRegister2 = new Register(operands[2]);
			instruction = new MULD(destinationRegister, sourceRegister1, sourceRegister2);
			break;
		case "DIVD":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			sourceRegister2 = new Register(operands[2]);
			instruction = new DIVD(destinationRegister, sourceRegister1, sourceRegister2);
			break;
		case "SUBD":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			sourceRegister2 = new Register(operands[2]);
			instruction = new SUBD(destinationRegister, sourceRegister1, sourceRegister2);
			break;
		case "J":
			instruction = new J(operands[0]);
			break;
		case "BEQ":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			instruction = new BEQ(destinationRegister, sourceRegister1, operands[2]);
			break;
		case "BNE":
			destinationRegister = new Register(operands[0]);
			sourceRegister1 = new Register(operands[1]);
			instruction = new BNE(destinationRegister, sourceRegister1, operands[2]);
			break;
		case "HLT":
			instruction = new HLT();
			break;
		default:
			throw new Error("Invalid Opcode: " + opcode);
		}
		instruction.label = label;

		return instruction;
	}
}
